package com.kunji.mijawharati.repository

import com.kunji.mijawharati.data.ContactDao
import com.kunji.mijawharati.model.Contact


class ContactRepository(private val contactDao: ContactDao) {
    val allContact = contactDao.getAllContact()

    suspend fun insert(contact: Contact) = contactDao.insertContact(contact)
    suspend fun update(contact: Contact) = contactDao.updateContact(contact)
    suspend fun delete(contact: Contact) = contactDao.deleteContact(contact)
    suspend fun getById(id: Int) = contactDao.getContactById(id)
}
