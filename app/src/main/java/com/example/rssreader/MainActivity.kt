package com.example.rssreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
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
                    composable("userList") { UserListScreen(navController, viewModel) }
                    composable("addUser") { AddUserScreen(navController, viewModel) }
                    composable("editUser/{userId}") { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
                        val user = userId?.let { id -> viewModel.users.find { it.id == id } }
                        if (user != null) {
                            EditUserScreen(navController, viewModel, user)
                        }
                    }
                }
            }
        }
    }
}
