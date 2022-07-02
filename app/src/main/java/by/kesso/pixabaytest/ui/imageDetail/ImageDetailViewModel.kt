package by.kesso.pixabaytest.ui.imageDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.kesso.pixabaytest.domain.entity.PixaImage

class ImageDetailViewModel: ViewModel() {

    private val _image = MutableLiveData<PixaImage>()
    val image: LiveData<PixaImage> = _image

    fun init(image: PixaImage) {
        _image.value = image
    }
}