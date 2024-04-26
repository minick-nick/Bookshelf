package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)
@Serializable
data class VolumeInfo(
    val title: String,
//  val authors: List<String>,
    val publisher: String = "",
    val publishedDate: String = "",
    val description: String = "",
    val imageLinks: ImageLinks
)
@Serializable
data class GoogleBook(
    val id: String,
    val volumeInfo: VolumeInfo
)
@Serializable
data class GoogleBooks(
    val totalItems: Int,
    val items: MutableList<GoogleBook>
)


