package com.kunji.mijawharati.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.kunji.mijawharati.model.CartItem

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems




    fun addItem(item: CartItem) {
        val existing = _cartItems.find { it.id == item.id }
        if (existing != null) {
            val index = _cartItems.indexOf(existing)
            _cartItems[index] = existing.copy(quantity = existing.quantity + 1)
        } else {
            _cartItems.add(item)
        }
    }

    fun removeItem(itemId: String) {
        _cartItems.removeAll { it.id == itemId }
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun getTotal(): Double {
        return _cartItems.sumOf { it.price * it.quantity }
    }
}
