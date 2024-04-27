package com.example.bookshelf.data

import com.example.bookshelf.model.GoogleBook
import com.example.bookshelf.model.GoogleBooks
import com.example.bookshelf.network.GoogleBooksApiService

interface GoogleBooksRepository {
    suspend fun getGoogleBooks(keyword: String): GoogleBooks
}

class NetworkGoogleBooksRepository(
    private val googleBooksApiService: GoogleBooksApiService
) : GoogleBooksRepository {
    override suspend fun getGoogleBooks(keyword: String)
        = googleBooksApiService.getGoogleBooks(keyword)
}