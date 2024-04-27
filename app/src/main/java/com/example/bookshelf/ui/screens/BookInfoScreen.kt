package com.example.bookshelf.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.GoogleBook
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun BookInfoScreen(
    book: GoogleBook,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = dimensionResource(R.dimen.extra_large_padding),
                end = dimensionResource(R.dimen.extra_large_padding),
                top = dimensionResource(R.dimen.large_padding)
            )
            .verticalScroll(rememberScrollState())
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.volumeInfo.imageLinks.thumb)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_connection_error),
                contentDescription = stringResource(R.string.book_photo),
                contentScale = ContentScale.FillHeight,
                modifier = modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.large_padding))
            )
        }
        Row {
            Text(
                text = stringResource(R.string.authors),
                style = MaterialTheme.typography.labelSmall,
                modifier = modifier.padding(end = dimensionResource(R.dimen.extra_small_padding))
            )
            Text(
                text = book.volumeInfo._authors.toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Row {
            Text(
                text = stringResource(R.string.publisher),
                style = MaterialTheme.typography.labelSmall,
                modifier = modifier.padding(end = dimensionResource(R.dimen.extra_small_padding))
            )
            Text(
                text = book.volumeInfo.publisher,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Row {
            Text(
                text = stringResource(R.string.published_date),
                style = MaterialTheme.typography.labelSmall,
                modifier = modifier.padding(end = dimensionResource(R.dimen.extra_small_padding))
            )
            Text(
                text = book.volumeInfo.publishedDate,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = book.volumeInfo.description,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookInfoCardPreview() {
    BookshelfTheme {
        Surface {
            val mockData = GoogleBook(
                id = "",
                volumeInfo = VolumeInfo(
                    title = "The History of Jazz",
                    //authors = listOf("Ted Gioia", "Dominic Dofredo"),
                    publisher = "Oxford University Press, USA",
                    publishedDate = "2021",
                    description = "A panoramic history of the genre brings to life the diverse " +
                            "places in which jazz evolved, traces the origins of its " +
                            "various styles, and offers commentary on the music itself",
                    imageLinks = ImageLinks(
                        smallThumbnail = "",
                        thumbnail = "http://books.google.com/books/content?id=EPUTEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                    )
                )
            )
            BookInfoScreen(
                book = mockData,
                onBack = {}
            )
        }
    }
}