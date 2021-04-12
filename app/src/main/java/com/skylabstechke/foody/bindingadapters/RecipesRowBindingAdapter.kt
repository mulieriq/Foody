package com.skylabstechke.foody.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter

class RecipesRowBindingAdapter {


    companion object {
        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }
    }
}