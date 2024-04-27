package com.example.bookshelf.fake

import android.util.Log
import com.example.bookshelf.rules.TestDispatcherRule
import com.example.bookshelf.ui.screens.BookshelfUiSate
import com.example.bookshelf.ui.screens.BookshelfViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun bookshelfViewModel_getGoogleBooks_verifyBookshelfUiStateSuccess() = runTest {
        val bookshelfViewModel = BookshelfViewModel(
            googleBooksRepository = FakeNetworkGoogleBooksRepository()
        )
        // This test is not yet completed
        bookshelfViewModel.getGoogleBooks("")
        assertEquals(
            BookshelfUiSate.Success(FakeDataSource.googleBooks),
            bookshelfViewModel.uiState.value.books
        )
    }
}