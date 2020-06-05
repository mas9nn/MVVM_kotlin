package com.example.whetherapp.ui.auth

import com.example.whetherapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse: User)
    fun onFailure(message:String)
}