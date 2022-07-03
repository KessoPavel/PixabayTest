package by.kesso.pixabaytest.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import by.kesso.pixabaytest.BuildConfig
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.domain.repository.PixeImageRepository
import by.kesso.pixabaytest.repository.remote.client.GetImages
import by.kesso.pixabaytest.repository.remote.client.PixabayClient
import by.kesso.pixabaytest.repository.remote.paging.ImageSource
import io.reactivex.rxjava3.core.Flowable

class RemoteRepository: PixeImageRepository {
    private val apiKey = BuildConfig.API_KEY

    override fun getAll(): Flowable<PagingData<PixaImage>> {
        val service = PixabayClient.client.create(GetImages::class.java)

        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { ImageSource(apiKey, service) }
        ).flowable
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 20, enablePlaceholders = false)
    }
}