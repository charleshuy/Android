package com.example.rssreader

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class) // ✅ Fix TopAppBar warning
@Composable
fun GroupDetailsScreen(navController: NavHostController, viewModel: UserViewModel, groupId: Int) {
    val group = viewModel.groups.find { it.id == groupId }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(group?.name ?: "Group Not Found") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("groupList") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            if (group != null) {
                FloatingActionButton(onClick = { navController.navigate("addMember/$groupId") }) {
                    Text("+")
                }
            }
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            if (group == null) {
                Text("Group not found", style = MaterialTheme.typography.headlineMedium)
                return@Column
            }

            Text("Group Members:", style = MaterialTheme.typography.headlineMedium)

            if (group.members.isEmpty()) {
                Text("No members in this group.", modifier = Modifier.padding(top = 8.dp))
            } else {
                LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
                    items(group.members) { user ->
                        Text("${user.username} - ${user.email}", modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }

            // ✅ Back Button Inside Content
            Button(
                onClick = { navController.navigate("groupList") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back to Groups")
            }
        }
    }
}
