package com.example.rssreader

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(navController: NavHostController, viewModel: UserViewModel) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("User List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addUser") }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            LazyColumn {
                items(viewModel.users) { user ->
                    UserItem(user, onEdit = {
                        navController.navigate("editUser/${user.id}")
                    }, onDelete = {
                        viewModel.deleteUser(user.id)
                    })
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Username: ${user.username}")
            Text("Full Name: ${user.fullName}")
            Text("Email: ${user.email}")

            Row(Modifier.padding(top = 8.dp)) {
                Button(onClick = onEdit, modifier = Modifier.padding(end = 8.dp)) {
                    Text("Edit")
                }
                Button(onClick = onDelete, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)) {
                    Text("Delete")
                }
            }
        }
    }
}
