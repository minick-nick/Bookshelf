package com.example.bookshelf.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.bookshelf.model.Book
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun HomeScreen(
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by bookshelfViewModel.uiState.collectAsState()

    Column(modifier = modifier) {
        SearchTextField(
            searchKeyword = uiState.searchKeyword,
            onSearchKeywordChange = { inputSearchKeyword ->
                bookshelfViewModel.updateSearchKeyword(inputSearchKeyword)
                                    },
            modifier = Modifier.fillMaxWidth()
        )

        // For testing purpose
        val mockData = List(5) { Book("https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png") }
        BookList(bookList = mockData)
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
    onSearchKeywordChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchKeyword,
        onValueChange = onSearchKeywordChange,
        singleLine = true,
        placeholder = { SearchTextPlaceHolder() },
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
    bookList: List<Book>,
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
            BookCard(book = it)
        }
    }
}

@Composable
fun BookCard(
    book: Book,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(book.imgSrc)
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
                onSearchKeywordChange = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    BookshelfTheme {
        Surface {
            val mockData = Book("https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png")
            BookCard(book = mockData)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookListPreview() {
    BookshelfTheme {
        Surface {
            val mockData = List(5) { Book("https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png") }
            BookList(bookList = mockData)
        }
    }
}


