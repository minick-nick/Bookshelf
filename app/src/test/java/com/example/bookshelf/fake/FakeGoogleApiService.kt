package com.example.bookshelf.fake

import com.example.bookshelf.model.GoogleBooks
import com.example.bookshelf.network.GoogleBooksApiService

class FakeGoogleApiService: GoogleBooksApiService {
    override suspend fun getGoogleBooks(keyword: String): GoogleBooks = FakeDataSource.getGoogleBooks(keyword)
}