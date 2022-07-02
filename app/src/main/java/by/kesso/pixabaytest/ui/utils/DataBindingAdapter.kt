package by.kesso.pixabaytest.ui.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import by.kesso.pixabaytest.R
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.ui.home.HomeViewModel
import by.kesso.pixabaytest.ui.home.adapter.ImageAdapter
import coil.load
import java.lang.Long.signum
import java.text.CharacterIterator
import java.text.StringCharacterIterator
import kotlin.math.log10
import kotlin.math.pow

object DataBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        url?.let {
            view.load(url) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("fileSize")
    fun fileSize(textView: TextView, size: Long?) {
        size?.let {
            textView.text = size.byte
        }
    }

    @JvmStatic
    @BindingAdapter("humanReadable")
    fun humanReadable(textView: TextView, count: Long?) {
        count?.let {
            textView.text = count.formatHumanReadable
        }
    }

    @JvmStatic
    @BindingAdapter("setAdapter")
    fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        view.adapter = adapter
    }

    private val Long.byte: String
        get() {
            val absB = if (this == Long.MIN_VALUE) Long.MAX_VALUE else Math.abs(this)
            if (absB < 1024) {
                return "$this B"
            }
            var value = absB
            val ci: CharacterIterator = StringCharacterIterator("KMGTPE")
            var i = 40
            while (i >= 0 && absB > 0xfffccccccccccccL shr i) {
                value = value shr 10
                ci.next()
                i -= 10
            }
            value *= signum(this)
            return String.format("%.1f %ciB", value / 1024.0, ci.current())
        }

    private val Long.formatHumanReadable: String
        get() {
            if (this <= 0) return "0"

            return log10(toDouble()).toInt().div(3).let {
                val precision = when (it) {
                    0 -> 0; else -> 1
                }
                val suffix = arrayOf("", "K", "M", "G", "T", "P", "E", "Z", "Y")
                String.format("%.${precision}f ${suffix[it]}", toDouble() / 10.0.pow(it * 3))
            }
        }
}