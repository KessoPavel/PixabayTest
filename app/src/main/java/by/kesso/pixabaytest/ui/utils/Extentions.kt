package by.kesso.pixabaytest.ui.utils

import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager

fun GridLayoutManager.spanSizeLookup(block: (position: Int) -> Int) = apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return block(position)
            }
        }
    }


fun ImageView.ScaleType.next(): ImageView.ScaleType {
    val index = ImageView.ScaleType.values().indexOf(this)
    return ImageView.ScaleType.values()[if (index == ImageView.ScaleType.values().size - 1) 0 else index + 1]
}