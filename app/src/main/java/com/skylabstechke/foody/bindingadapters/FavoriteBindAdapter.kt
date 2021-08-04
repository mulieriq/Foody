package com.skylabstechke.foody.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.skylabstechke.foody.data.room.FavoriteEntity

class FavoriteBindAdapter {

    companion object {
        @BindingAdapter("readDFavoriteDatabase")
        @JvmStatic
        fun favoriteImageViewVisibility(image: ImageView, database: List<FavoriteEntity>?) {
            if (database.isNullOrEmpty()) {
                image.visibility = View.VISIBLE
            } else {
                image.visibility = View.INVISIBLE
            }
        }


        @BindingAdapter("readDFavoriteDatabase2")
        @JvmStatic
        fun favoriteTextViewVisibility(text: TextView, database: List<FavoriteEntity>?) {

            if (database.isNullOrEmpty()) {
                text.visibility = View.VISIBLE
            } else {
                text.visibility = View.INVISIBLE
            }
        }
    }


}