package com.example.whetherapp.data.network.responses

import com.example.whetherapp.data.db.entities.User

data class AuthResponse(
    val isSuccessfull:Boolean?,
    val message:String?,
    val user:User?
)