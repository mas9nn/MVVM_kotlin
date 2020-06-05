package com.example.whetherapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.whetherapp.data.repositories.UserRepository

class ProfileViewModel (repository: UserRepository): ViewModel() {
    val user = repository.getUser()
}
