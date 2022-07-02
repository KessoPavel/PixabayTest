package by.kesso.pixabaytest.repository.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagePage(
    @field:Json(name = "hits")
    val hits: List<RemoteImage?>?,
    @field:Json(name = "total")
    val total: Int?,
    @field:Json(name = "totalHits")
    val totalHits: Int?
)