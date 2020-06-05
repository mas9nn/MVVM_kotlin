package com.example.whetherapp.ui.home.quates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whetherapp.data.repositories.QuoteRepository
import com.example.whetherapp.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(private val repository: QuoteRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuatesViewModel(repository) as T
    }
}