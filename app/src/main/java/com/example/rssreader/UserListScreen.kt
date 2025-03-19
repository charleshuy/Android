package com.example.rssreader

import android.widget.ImageView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.squareup.picasso.Picasso

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(navController: NavHostController, viewModel: UserViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("User List") },
                actions = {
                    Button(onClick = { navController.navigate("groupList") }) {
                        Text("Groups")
                    }
                }
            )
        },
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
        Row(Modifier.padding(16.dp)) {
            // Load Image Using Picasso
            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        Picasso.get()
                            .load(user.imageUrl)
//                            .placeholder(android.R.drawable.ic_menu_report_image) // Add a local placeholder image
//                            .error(android.R.drawable.stat_notify_error) // Add a local error image
                            .into(this)
                    }
                },
                modifier = Modifier.size(64.dp).padding(end = 16.dp)
            )

            Column {
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
}

