package com.kunji.mijawharati.ui.theme.screens.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.model.Contact
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.navigation.ROUT_FAVORITES
import com.kunji.mijawharati.navigation.ROUT_HOME
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadContactScreen(
    navController: NavController,
    contactViewModel: ContactViewModel,
    editingContactId: Int? = null
) {
    var selectedIndex by remember { mutableStateOf(0) }

    // Form state
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    // Load contact for editing
    LaunchedEffect(editingContactId) {
        if (editingContactId != null) {
            contactViewModel.loadContactById(editingContactId)
        }
    }
    val editingContact = contactViewModel.selectedContact.collectAsState().value

    LaunchedEffect(editingContact) {
        editingContact?.let {
            name = it.name
            email = it.email
            subject = it.subject
            message = it.message
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contact us", color = CreamWhite) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = CreamWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen,
                    titleContentColor = CreamWhite
                )
            )
        },

        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Background Image
                Image(
                    painter = painterResource(id = R.drawable.img_10),
                    contentDescription = "Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Form content with translucent cream-white background
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(
                            color = CreamWhite.copy(alpha = 0.85f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = subject,
                        onValueChange = { subject = it },
                        label = { Text("Subject") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))

                    // Updated Message Field (larger)
                    OutlinedTextField(
                        value = message,
                        onValueChange = { message = it },
                        label = { Text("Message") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        maxLines = 6
                    )
                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val contact = Contact(
                                id = editingContact?.id ?: 0,
                                name = name,
                                email = email,
                                subject = subject,
                                message = message
                            )
                            if (editingContact != null) {
                                contactViewModel.update(contact)
                            } else {
                                contactViewModel.insert(contact)
                            }

                            // Clear fields after submit
                            name = ""
                            email = ""
                            subject = ""
                            message = ""

                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EmeraldGreen,
                            contentColor = CreamWhite
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (editingContact != null) "Update Contact" else "Submit")
                    }
                }
            }
        }
    )
}
