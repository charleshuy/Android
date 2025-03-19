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

    private val _groups = mutableStateListOf<Group>()
    val groups: List<Group> get() = _groups

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

    fun addGroup(group: Group) {
        _groups.add(group)
    }

    fun addUserToGroup(groupId: Int, user: User) {
        _groups.find { it.id == groupId }?.members?.add(user)
    }

    fun removeUserFromGroup(groupId: Int, userId: Int) {
        _groups.find { it.id == groupId }?.members?.removeIf { it.id == userId }
    }
}
