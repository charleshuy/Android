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
fun GroupListScreen(navController: NavHostController, viewModel: UserViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Groups") },
                actions = {
                    Button(onClick = { navController.navigate("userList") }) {
                        Text("Users")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addGroup") }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            LazyColumn {
                items(viewModel.groups) { group ->
                    GroupItem(group, onView = { navController.navigate("groupDetails/${group.id}") })
                }
            }
        }
    }
}


@Composable
fun GroupItem(group: Group, onView: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(Modifier.padding(16.dp)) {
            Column {
                Text("Group Name: ${group.name}")
                Text("Members: ${group.members.size}")

                Button(onClick = onView) {
                    Text("View Group")
                }
            }
        }
    }
}
