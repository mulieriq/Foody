package com.skylabstechke.foody.bindingadapters

import android.media.Image
import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.skylabstechke.foody.data.room.FavoriteEntity

object FavoriteBindAdapter {

    @JvmStatic
    @BindingAdapter("readDatabase" ,requireAll = true)
    fun favoriteImageViewVisibility(image: ImageView,database:List<FavoriteEntity>){
         if (!database.isNullOrEmpty()){
             image.visibility = View.INVISIBLE
         }else{
             image.visibility = View.VISIBLE
         }
    }
}