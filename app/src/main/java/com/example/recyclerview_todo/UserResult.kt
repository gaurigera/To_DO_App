package com.example.recyclerview_todo

data class UserResult(
    val data : UserData?,
    val errorMessage: String?
)
data class UserData(
    val userId: String,
    val userName : String?,
    val profilePicURL : String?
)
