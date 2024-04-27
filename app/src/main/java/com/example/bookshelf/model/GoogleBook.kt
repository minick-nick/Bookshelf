package com.example.bookshelf.model

import com.example.bookshelf.helpers.formatListOfNames
import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    private val smallThumbnail: String = "",
    private val thumbnail: String = ""
) {
    val sThumb
        get() = smallThumbnail.replace("http", "https")
    val thumb
        get() = thumbnail.replace("http", "https")
}
@Serializable
data class VolumeInfo(
    val title: String = "",
    private val authors: List<String> = listOf(),
    val publisher: String = "",
    val publishedDate: String = "",
    val description: String = "",
    val imageLinks: ImageLinks = ImageLinks()
) {
    val _authors: String
        get() = formatListOfNames(authors)
}
@Serializable
data class GoogleBook(
    val id: String = "",
    val volumeInfo: VolumeInfo = VolumeInfo()
)
@Serializable
data class GoogleBooks(
    val totalItems: Int = 0,
    val items: MutableList<GoogleBook> = mutableListOf()
)


