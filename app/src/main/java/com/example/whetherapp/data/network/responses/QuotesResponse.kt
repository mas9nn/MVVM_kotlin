package com.example.whetherapp.data.network.responses

import com.example.whetherapp.data.db.entities.Quote

data class QuotesResponse (
    val isSuccess:Boolean,
    val quotes:List<Quote>
)