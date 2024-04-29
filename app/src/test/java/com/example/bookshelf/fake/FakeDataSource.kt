package com.example.bookshelf.fake

import com.example.bookshelf.model.GoogleBook
import com.example.bookshelf.model.GoogleBooks
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo

object FakeDataSource {
    private val googleBook1 = GoogleBook(
        id = "id1",
        volumeInfo = VolumeInfo(
            title = "title 1",
            authors = listOf("author1", "author2"),
            publisher = "publisher1",
            publishedDate = "published date1",
            description = "description1",
            imageLinks = ImageLinks(
                smallThumbnail = "small thumbnail1",
                thumbnail = "thumbnail1"
            )
        )
    )

    private val googleBook2 = GoogleBook(
        id = "id2",
        volumeInfo = VolumeInfo(
            title = "title 2",
            authors = listOf("author1", "author2"),
            publisher = "publisher2",
            publishedDate = "published date2",
            description = "description2",
            imageLinks = ImageLinks(
                smallThumbnail = "small thumbnail2",
                thumbnail = "thumbnail2"
            )
        )
    )

    private val googleBook3 = GoogleBook(
        id = "id3",
        volumeInfo = VolumeInfo(
            title = "title 3",
            authors = listOf("author1", "author2"),
            publisher = "publisher3",
            publishedDate = "published date3",
            description = "description3",
            imageLinks = ImageLinks(
                smallThumbnail = "small thumbnail3",
                thumbnail = "thumbnail3"
            )
        )
    )

    private val googleBooks = mutableListOf(
        googleBook1,
        googleBook2,
        googleBook3
    )

    fun getGoogleBooks(keyword: String): GoogleBooks {
        val result = mutableListOf<GoogleBook>()

        googleBooks.forEach {
            if (it.volumeInfo.title.contains(keyword)) result.add(it)
        }

        return GoogleBooks(totalItems = result.size, items = result)
    }
}