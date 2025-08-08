package com.kunji.mijawharati.model

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val phone: String,
    val imagePath: String
)
