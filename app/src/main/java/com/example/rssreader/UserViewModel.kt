package com.example.rssreader

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class UserViewModel : ViewModel() {
    private val _users = mutableStateListOf(
        User(1, "user1", "John Doe", "john@example.com", "https://i.imgur.com/dTxb2no.jpeg"),
        User(2, "user2", "Jane Smith", "jane@example.com", "https://i.imgur.com/dTxb2no.jpeg"),
        User(3, "user3", "Alice Johnson", "alice@example.com", "https://i.imgur.com/dTxb2no.jpeg")
    )
    val users: List<User> get() = _users

    fun addUser(user: User) {
        _users.add(user)
    }

    fun updateUser(updatedUser: User) {
        val index = _users.indexOfFirst { it.id == updatedUser.id }
        if (index != -1) {
            _users[index] = updatedUser
        }
    }

    fun deleteUser(userId: Int) {
        _users.removeAll { it.id == userId }
    }
}
