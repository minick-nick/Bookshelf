package com.example.bookshelf.ui.screens

data class UiState(
    val searchKeyword: String = "",
    val currentScreen: Screens = Screens.HomeScreen,
    val books: BookshelfUiSate = BookshelfUiSate.Loading
)
