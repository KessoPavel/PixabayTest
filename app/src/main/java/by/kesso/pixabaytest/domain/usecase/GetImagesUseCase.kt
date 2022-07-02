package by.kesso.pixabaytest.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.domain.repository.PixeImageRepository
import io.reactivex.rxjava3.core.Flowable

class GetImagesUseCase(
    private val repository: PixeImageRepository
) {
    fun get(): LiveData<PagingData<PixaImage>> {
        return repository.getAll()
    }
}