package com.example.whetherapp.ui.home.quates

import androidx.lifecycle.ViewModel
import com.example.whetherapp.data.repositories.QuoteRepository
import com.example.whetherapp.util.lazyDeferred

class QuatesViewModel (
    repository: QuoteRepository
): ViewModel() {
    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
