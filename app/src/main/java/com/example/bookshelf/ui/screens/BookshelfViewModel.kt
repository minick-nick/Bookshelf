package com.example.bookshelf.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookshelfViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun updateSearchKeyword(inputSearchKeyword: String) {
        _uiState.update { currentUiState ->
            currentUiState.copy(searchKeyword = inputSearchKeyword)
        }
    }
}