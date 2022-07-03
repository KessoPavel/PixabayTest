package by.kesso.pixabaytest.repository.remote.client

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object PixabayClient {
    private const val baseUrl = "https://pixabay.com"

    val client: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())

            .build()
    }
}