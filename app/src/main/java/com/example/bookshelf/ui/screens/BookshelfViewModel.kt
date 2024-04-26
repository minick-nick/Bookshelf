package com.example.bookshelf.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.GoogleBooksRepository
import com.example.bookshelf.model.GoogleBooks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookshelfUiSate {
    data class Success(val googleBooks: GoogleBooks) : BookshelfUiSate
    object Error : BookshelfUiSate
    object Loading : BookshelfUiSate
}

class BookshelfViewModel(private val googleBooksRepository: GoogleBooksRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun updateSearchKeyword(inputSearchKeyword: String) {
        _uiState.update { currentUiState ->
            currentUiState.copy(searchKeyword = inputSearchKeyword)
        }
    }

    fun getGoogleBooks(keyword: String) {
        viewModelScope.launch {
            val result = try {
                val result = googleBooksRepository.getGoogleBooks(keyword)
                Log.i("getGoogleBooks", result.toString())
                BookshelfUiSate.Success(result)
            } catch (e: IOException) {
                BookshelfUiSate.Error
            }
            _uiState.update { currentState ->
                currentState.copy(
                    books = result
                )
            }
        }
    }

    fun changeScreen(screen: Screens) {
        _uiState.update { currentState ->
            currentState.copy(
                currentScreen = screen
            )
        }
    }

    fun getGoogleBookUsingVolumeId(volumeId: String) {
        viewModelScope.launch {
            try {
                val result = googleBooksRepository.getGoogleBookUsingVolumeId(volumeId)
                Log.i("getGoogleBookUsingVolumeId", result.toString())
            } catch (e: IOException) {

            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val googleBooksRepository = application.container.googleBooksRepository
                BookshelfViewModel(googleBooksRepository)
            }
        }
    }
}