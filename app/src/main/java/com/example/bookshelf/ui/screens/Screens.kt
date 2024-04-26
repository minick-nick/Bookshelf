package com.example.bookshelf.ui.screens

import com.example.bookshelf.model.GoogleBook


sealed interface Screens {
    object HomeScreen: Screens
    data class BookInfoScreen(val book: GoogleBook) : Screens
}