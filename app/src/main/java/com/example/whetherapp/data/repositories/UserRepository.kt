package com.example.whetherapp.data.repositories

import com.example.whetherapp.data.db.AppDatabase
import com.example.whetherapp.data.db.entities.User
import com.example.whetherapp.data.network.responses.AuthResponse
import com.example.whetherapp.data.network.MyApi
import com.example.whetherapp.data.network.SafeApiRequest

class UserRepository(private val api: MyApi,private val db:AppDatabase) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }
    suspend fun saveUser(user:User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()

    suspend fun userSignUp(name:String,email: String,password:String): AuthResponse {
        return apiRequest { api.signUp(name, email, password) }
    }
}