package com.example.bookshelf.fake

import com.example.bookshelf.data.NetworkGoogleBooksRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkGoogleBooksRepositoryTest {
    @Test
    fun networkGoogleBooksRepository_getGoogleBooks_verifyGoogleBooks() = runTest {
        val fakeGoogleApiService = FakeGoogleApiService()
        val repository = NetworkGoogleBooksRepository(fakeGoogleApiService)
        val keyword = "title"
        assertEquals(FakeDataSource.getGoogleBooks(keyword), repository.getGoogleBooks(keyword))
    }
}