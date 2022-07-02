package by.kesso.pixabaytest.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PixaImage(
    val id: Long,

    val type: String?,

    val largeImageURL: String?,
    val imageHeight: Int?,
    val imageSize: Long?,
    val imageWidth: Int?,

    val webFormatURL: String?,
    val webFormatHeight: Int?,
    val webFormatWidth: Int?,

    val previewURL: String?,
    val previewWidth: Int?,
    val previewHeight: Int?,

    val pageURL: String?,

    val likes: Long?,
    val views: Long?,
    val downloads: Long?,
    val comments: Long?,
    val tags: String?,
    val collections: Long?,

    val user: String?,
    val userId: Long?,
    val userImageURL: String?,
): Parcelable
