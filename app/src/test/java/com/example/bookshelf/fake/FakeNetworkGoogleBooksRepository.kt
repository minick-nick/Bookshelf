package com.example.bookshelf.fake

import com.example.bookshelf.data.GoogleBooksRepository
import com.example.bookshelf.model.GoogleBooks

class FakeNetworkGoogleBooksRepository: GoogleBooksRepository {
    override suspend fun getGoogleBooks(keyword: String) = FakeDataSource.getGoogleBooks(keyword)
}