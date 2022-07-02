package by.kesso.pixabaytest.ui.imageDetail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.kesso.pixabaytest.R
import by.kesso.pixabaytest.domain.entity.PixaImage
import coil.load

class ImageDetailViewModel: ViewModel() {

    private val _image = MutableLiveData<PixaImage>()
    val image: LiveData<PixaImage> = _image

    fun init(image: PixaImage) {
        _image.value = image
    }
}