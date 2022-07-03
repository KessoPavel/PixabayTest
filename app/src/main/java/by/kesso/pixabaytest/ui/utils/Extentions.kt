package by.kesso.pixabaytest.ui.utils

import androidx.recyclerview.widget.GridLayoutManager

fun GridLayoutManager.spanSizeLookup(block: (position: Int) -> Int) = apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return block(position)
            }
        }
    }
