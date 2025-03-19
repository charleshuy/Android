package com.example.rssreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.rssreader.ui.theme.UserManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserManagementTheme {
                val navController = rememberNavController()
                val viewModel = remember { UserViewModel() }

                NavHost(navController, startDestination = "userList") {
                    // User-related screens
                    composable("userList") { UserListScreen(navController, viewModel) }
                    composable("addUser") { AddUserScreen(navController, viewModel) }
                    composable("editUser/{userId}") { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
                        val user = userId?.let { id -> viewModel.users.find { it.id == id } }
                        if (user != null) {
                            EditUserScreen(navController, viewModel, user)
                        }
                    }

                    // Group-related screens
                    composable("groupList") { GroupListScreen(navController, viewModel) } // ✅ View groups
                    composable("addGroup") { AddGroupScreen(navController, viewModel) } // ✅ Create group
                    composable("groupDetails/{groupId}") { backStackEntry ->
                        val groupId = backStackEntry.arguments?.getString("groupId")?.toIntOrNull()
                        if (groupId != null) {
                            GroupDetailsScreen(navController, viewModel, groupId) // ✅ View group details
                        }
                    }
                    composable("addMember/{groupId}") { backStackEntry ->
                        val groupId = backStackEntry.arguments?.getString("groupId")?.toIntOrNull()
                        if (groupId != null) {
                            AddMemberScreen(navController, viewModel, groupId) // ✅ Add members
                        }
                    }
                }
            }
        }
    }
}
