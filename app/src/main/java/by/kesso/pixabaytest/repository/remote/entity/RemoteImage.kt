package by.kesso.pixabaytest.repository.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteImage(
    @field:Json(name = "collections")
    val collections: Long?,
    @field:Json(name = "comments")
    val comments: Long?,
    @field:Json(name = "downloads")
    val downloads: Long?,
    @field:Json(name = "id")
    val id: Long?,
    @field:Json(name = "imageHeight")
    val imageHeight: Int?,
    @field:Json(name = "imageSize")
    val imageSize: Long?,
    @field:Json(name = "imageWidth")
    val imageWidth: Int?,
    @field:Json(name = "largeImageURL")
    val largeImageURL: String?,
    @field:Json(name = "likes")
    val likes: Long?,
    @field:Json(name = "pageURL")
    val pageURL: String?,
    @field:Json(name = "previewHeight")
    val previewHeight: Int?,
    @field:Json(name = "previewURL")
    val previewURL: String?,
    @field:Json(name = "previewWidth")
    val previewWidth: Int?,
    @field:Json(name = "tags")
    val tags: String?,
    @field:Json(name = "type")
    val type: String?,
    @field:Json(name = "user")
    val user: String?,
    @field:Json(name = "user_id")
    val userId: Long?,
    @field:Json(name = "userImageURL")
    val userImageURL: String?,
    @field:Json(name = "views")
    val views: Long?,
    @field:Json(name = "webformatHeight")
    val webFormatHeight: Int?,
    @field:Json(name = "webformatURL")
    val webFormatURL: String?,
    @field:Json(name = "webformatWidth")
    val webFormatWidth: Int?
)