package com.example.rssreader

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class) // ✅ Fix TopAppBar warning
@Composable
fun AddGroupScreen(navController: NavHostController, viewModel: UserViewModel) {
    var groupName by remember { mutableStateOf(TextFieldValue()) }
    val isGroupNameValid = groupName.text.isNotBlank()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Add Group") }) } // ✅ Better UI
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = groupName,
                onValueChange = { groupName = it },
                label = { Text("Group Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.addGroup(Group(id = viewModel.groups.size + 1, name = groupName.text.trim()))
                    navController.popBackStack()
                },
                enabled = isGroupNameValid, // ✅ Prevent adding empty group names
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Group")
            }
        }
    }
}
