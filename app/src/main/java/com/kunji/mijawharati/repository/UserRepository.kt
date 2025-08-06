package com.kunji.mijawharati.repository

import com.kunji.mijawharati.data.UserDao
import com.kunji.mijawharati.model.User


class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}