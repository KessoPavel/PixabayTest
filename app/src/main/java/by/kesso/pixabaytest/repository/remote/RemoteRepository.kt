package by.kesso.pixabaytest.repository.remote

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import androidx.paging.rxjava3.observable
import by.kesso.pixabaytest.BuildConfig
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.domain.repository.PixeImageRepository
import by.kesso.pixabaytest.repository.remote.client.GetImages
import by.kesso.pixabaytest.repository.remote.client.PixabayClient
import by.kesso.pixabaytest.repository.remote.mapper.Mapper
import by.kesso.pixabaytest.repository.remote.paging.ImageSource
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

class RemoteRepository: PixeImageRepository {
    private val apiKey = BuildConfig.API_KEY

    override fun getAll(): Flowable<PagingData<PixaImage>> {
        val service = PixabayClient.client.create(GetImages::class.java)

        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { ImageSource(apiKey, service) }
        ).flowable
    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 20, enablePlaceholders = false)
    }
}