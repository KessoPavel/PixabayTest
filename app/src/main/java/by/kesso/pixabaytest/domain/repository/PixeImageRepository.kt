package by.kesso.pixabaytest.domain.repository

import androidx.paging.PagingData
import by.kesso.pixabaytest.domain.entity.PixaImage
import io.reactivex.rxjava3.core.Flowable

interface PixeImageRepository {
    fun getAll(): Flowable<PagingData<PixaImage>>
}