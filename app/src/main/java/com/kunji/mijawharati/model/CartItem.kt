package com.kunji.mijawharati.model

data class CartItem(
    val id: String,
    val name: String,
    val price: Double,
    val imagePath: String?, // ✅ changed from Int to String? for URI/path
    val quantity: Int = 1
)

