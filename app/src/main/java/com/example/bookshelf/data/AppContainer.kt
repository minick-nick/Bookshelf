package com.example.bookshelf.data

import com.example.bookshelf.network.GoogleBooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val googleBooksRepository: GoogleBooksRepository
}

class DefaultAppContainer : AppContainer {
    private val base_url = "https://www.googleapis.com"

    private val json = Json{ ignoreUnknownKeys = true }
    private val contentType = "application/json".toMediaType()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory(contentType))
        .baseUrl(base_url)
        .build()

    private val retrofitService: GoogleBooksApiService by lazy {
        retrofit.create(GoogleBooksApiService::class.java)
    }

    override val googleBooksRepository: GoogleBooksRepository by lazy {
        NetworkGoogleBooksRepository(retrofitService)
    }
}