package com.example.bookshelf.network

import com.example.bookshelf.model.GoogleBook
import com.example.bookshelf.model.GoogleBooks
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApiService {
    @GET("books/v1/volumes")
    suspend fun getGoogleBooks(@Query("q") keyword: String): GoogleBooks

    @GET("books/v1/volumes/{volumeId}")
    suspend fun getGoogleBookUsingVolumeId(@Path("volumeId") volumeId: String): GoogleBook
}


