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
fun EditUserScreen(navController: NavHostController, viewModel: UserViewModel, user: User) {
    var username by remember { mutableStateOf(TextFieldValue(user.username)) }
    var fullName by remember { mutableStateOf(TextFieldValue(user.fullName)) }
    var email by remember { mutableStateOf(TextFieldValue(user.email)) }

    Scaffold(topBar = { TopAppBar(title = { Text("Edit User") }) }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
            TextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") })
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

            Row(Modifier.padding(top = 16.dp)) {
                Button(onClick = {
                    viewModel.updateUser(User(user.id, username.text, fullName.text, email.text))
                    navController.popBackStack()
                }) { Text("Update") }

                Spacer(Modifier.width(8.dp))

                Button(onClick = { navController.popBackStack() }) {
                    Text("Cancel")
                }
            }
        }
    }
}
