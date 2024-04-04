package com.example.bookshelf.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.BookshelfViewModel
import com.example.bookshelf.ui.screens.HomeScreen
import com.example.bookshelf.ui.screens.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar(title = R.string.app_name) }
    ) {
        val bookShelfViewModel: BookshelfViewModel = viewModel()

        HomeScreen(
            bookshelfViewModel = bookShelfViewModel,
            modifier = modifier.padding(it)
        )
    }
}

