package by.kesso.pixabaytest.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PixaImage(
    val id: Int,

    val type: String?,

    val largeImageURL: String?,
    val imageHeight: Int?,
    val imageSize: Int?,
    val imageWidth: Int?,

    val webFormatURL: String?,
    val webFormatHeight: Int?,
    val webFormatWidth: Int?,

    val previewURL: String?,
    val previewWidth: Int?,
    val previewHeight: Int?,

    val pageURL: String?,

    val likes: Int?,
    val views: Int?,
    val downloads: Int?,
    val comments: Int?,
    val tags: String?,
    val collections: Int?,

    val user: String?,
    val userId: Int?,
    val userImageURL: String?,
): Parcelable
