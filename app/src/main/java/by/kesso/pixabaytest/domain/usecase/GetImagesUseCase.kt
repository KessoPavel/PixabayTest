package by.kesso.pixabaytest.domain.usecase

import androidx.paging.PagingData
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.domain.repository.PixeImageRepository
import io.reactivex.rxjava3.core.Flowable

class GetImagesUseCase(
    private val repository: PixeImageRepository
) {
    fun get(): Flowable<PagingData<PixaImage>> {
        return repository.getAll()
    }
}