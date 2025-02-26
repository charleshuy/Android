package com.example.rssreader

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserScreen(navController: NavHostController, viewModel: UserViewModel) {
    var username by remember { mutableStateOf(TextFieldValue()) }
    var fullName by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }

    Scaffold(topBar = { TopAppBar(title = { Text("Add User") }) }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
            TextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") })
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

            Row(Modifier.padding(top = 16.dp)) {
                Button(onClick = {
                    viewModel.addUser(User(viewModel.users.size + 1, username.text, fullName.text, email.text))
                    navController.popBackStack()
                }) { Text("Save") }

                Spacer(Modifier.width(8.dp))

                Button(onClick = { navController.popBackStack() }) {
                    Text("Cancel")
                }
            }
        }
    }
}
