package by.kesso.pixabaytest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.domain.usecase.GetImagesUseCase

class HomeViewModel(
    getImagesUseCase: GetImagesUseCase,
): ViewModel() {

    val list: LiveData<PagingData<PixaImage>> by lazy {
        getImagesUseCase.get().cachedIn(viewModelScope).toLiveData()
    }
}