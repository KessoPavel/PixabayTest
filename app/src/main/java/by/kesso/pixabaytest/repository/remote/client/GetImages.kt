package by.kesso.pixabaytest.repository.remote.client

import by.kesso.pixabaytest.repository.remote.entity.ImagePage
import by.kesso.pixabaytest.repository.remote.entity.RemoteImage
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface GetImages {
    @GET("api/")
    fun get(
        @Query("key") apiKey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("safesearch") safeSearch: Boolean = true,
        @Query("order") order: String = "popular",
    ): Single<ImagePage>
}