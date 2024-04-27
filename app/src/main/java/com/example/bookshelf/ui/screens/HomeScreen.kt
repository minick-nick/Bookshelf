package com.example.bookshelf.ui.screens

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.GoogleBook
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun HomeScreen(
    uiState: UiState,
    onBookClick: (GoogleBook) -> Unit,
    onSearchKeywordChange: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SearchTextField(
            searchKeyword = uiState.searchKeyword,
            onSearchKeywordChange = { inputSearchKeyword ->
                onSearchKeywordChange(inputSearchKeyword)
                                    },
            onKeyboardDone = onKeyboardDone,
            modifier = Modifier.fillMaxWidth()
        )

        when(uiState.books) {
            is BookshelfUiSate.Success -> BookList(
                bookList = uiState.books.googleBooks.items,
                onBookClick = { selectedBook ->
                    onBookClick(selectedBook)
                }
            )
            is BookshelfUiSate.Loading -> Text(text = "Loading...")
            is BookshelfUiSate.Error -> Text(text = "Error!")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title)) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchKeyword: String,
    onSearchKeywordChange: (String) -> Unit,
    onKeyboardDone: () -> Unit
) {
    OutlinedTextField(
        value = searchKeyword,
        onValueChange = onSearchKeywordChange,
        singleLine = true,
        placeholder = { SearchTextPlaceHolder() },
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        ),
        modifier = modifier
    )
}

@Composable
fun SearchTextPlaceHolder(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            stringResource(R.string.search_books),
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = stringResource(R.string.search_icon)
        )
    }
}

@Composable
fun BookList(
    bookList: MutableList<GoogleBook>,
    onBookClick: (GoogleBook) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(dimensionResource(R.dimen.small_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_small_padding)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_small_padding)),
        modifier = modifier
    ) {
        items(bookList) {
            BookCard(
                book = it,
                onBookClick = { selectedBook ->
                    onBookClick(selectedBook)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookCard(
    book: GoogleBook,
    modifier: Modifier = Modifier,
    onBookClick: (GoogleBook) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onBookClick(book) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(book.volumeInfo.imageLinks.thumb)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.ic_broken_image),
            contentDescription = stringResource(R.string.book_photo),
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    BookshelfTheme {
        Surface {
            TopAppBar(title = R.string.app_name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    BookshelfTheme {
        Surface {
            SearchTextField(
                searchKeyword = "",
                onSearchKeywordChange = {},
                onKeyboardDone = {}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    BookshelfTheme {
        Surface {
            val mockData = GoogleBook(
                id = "",
                volumeInfo = VolumeInfo(
                    title = "",
                    //authors = listOf("", ""),
                    publisher = "",
                    publishedDate = "",
                    description = "",
                    imageLinks = ImageLinks(
                        smallThumbnail = "",
                        thumbnail = ""
                    )
                )
            )
            BookCard(
                book = mockData,
                onBookClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookListPreview() {
    BookshelfTheme {
        Surface {
            val mockData = MutableList(3) {
                GoogleBook(
                    id = "",
                    volumeInfo = VolumeInfo(
                        title = "",
                        //authors = listOf("", ""),
                        publisher = "",
                        publishedDate = "",
                        description = "",
                        imageLinks = ImageLinks(
                            smallThumbnail = "",
                            thumbnail = ""
                        )
                    )
                )
            }
            BookList(
                bookList = mockData,
                onBookClick = {}
            )
        }
    }
}



