package com.example.bookshelf.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.BookInfoScreen
import com.example.bookshelf.ui.screens.BookshelfViewModel
import com.example.bookshelf.ui.screens.HomeScreen
import com.example.bookshelf.ui.screens.Screens
import com.example.bookshelf.ui.screens.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar(title = R.string.app_name) }
    ) {
        val bookShelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
        val uiState by bookShelfViewModel.uiState.collectAsState()

        when(uiState.currentScreen) {
            is Screens.HomeScreen -> {
                HomeScreen(
                    uiState = uiState,
                    onBookClick = { selectedBook ->
                        bookShelfViewModel.changeScreen(Screens.BookInfoScreen(book = selectedBook))
                    },
                    onSearchKeywordChange = { keyword ->
                        bookShelfViewModel.updateSearchKeyword(keyword) },
                    onKeyboardDone = { bookShelfViewModel.getGoogleBooks(uiState.searchKeyword) },
                    modifier = modifier.padding(it)
                )
            }
            is Screens.BookInfoScreen -> {
                val selectedBook = (uiState.currentScreen as Screens.BookInfoScreen).book

                BookInfoScreen(
                    book = selectedBook,
                    onBack = { bookShelfViewModel.changeScreen(Screens.HomeScreen) }
                )
            }
        }
    }
}

