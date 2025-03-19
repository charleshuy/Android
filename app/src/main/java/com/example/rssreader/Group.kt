package com.example.rssreader

data class Group (
    val id: Int,
    var name: String,
    val members: MutableList<User> = mutableListOf()
)

