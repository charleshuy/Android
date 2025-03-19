package com.example.rssreader

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class) // ✅ Fix experimental API warning
@Composable
fun AddMemberScreen(navController: NavHostController, viewModel: UserViewModel, groupId: Int) {
    val group = viewModel.groups.find { it.id == groupId }

    if (group == null) {
        Text("Group not found", modifier = Modifier.padding(16.dp))
        return
    }

    val availableUsers = remember {
        viewModel.users.filter { user -> group.members.none { it.id == user.id } }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Add Members") }) // ✅ Centered title
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            if (availableUsers.isEmpty()) {
                Text("No available users to add", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn {
                    items(availableUsers) { user ->
                        Row(
                            Modifier.fillMaxWidth().padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(user.username, modifier = Modifier.weight(1f))
                            Button(onClick = {
                                viewModel.addUserToGroup(groupId, user)
                                navController.popBackStack()
                            }) {
                                Text("Add")
                            }
                        }
                    }
                }
            }
        }
    }
}
