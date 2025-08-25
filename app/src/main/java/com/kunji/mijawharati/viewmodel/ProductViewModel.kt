package com.kunji.mijawharati.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kunji.mijawharati.data.ProductDatabase
import com.kunji.mijawharati.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ProductViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val productDao = ProductDatabase.getDatabase(app).productDao()

    // ✅ All products
    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()

    // ✅ Favorite products
    val favoriteProducts: LiveData<List<Product>> = productDao.getFavoriteProducts()

    // ✅ Cart (in-memory, not persisted in DB for now)
    private val _cartProducts = MutableLiveData<List<Product>>(emptyList())
    val cartProducts: LiveData<List<Product>> get() = _cartProducts

    fun addProduct(name: String, price: Double, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newProduct = Product(
                name = name,
                price = price,
                phone = phone,
                imagePath = savedImagePath,
                isFavorite = false // default
            )
            productDao.insertProduct(newProduct)
        }
    }

    fun updateProduct(updatedProduct: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productDao.updateProduct(updatedProduct)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteImageFromInternalStorage(product.imagePath)
            productDao.deleteProduct(product)
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            val newState = !product.isFavorite
            productDao.setFavorite(product.id, newState)
        }
    }

    // ✅ CART FUNCTIONS
    fun addToCart(product: Product) {
        val currentCart = _cartProducts.value?.toMutableList() ?: mutableListOf()
        if (!currentCart.contains(product)) {
            currentCart.add(product)
            _cartProducts.value = currentCart
        }
    }

    fun removeFromCart(product: Product) {
        val currentCart = _cartProducts.value?.toMutableList() ?: mutableListOf()
        currentCart.remove(product)
        _cartProducts.value = currentCart
    }

    fun clearCart() {
        _cartProducts.value = emptyList()
    }

    // ✅ Private helper methods for saving/deleting images
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
