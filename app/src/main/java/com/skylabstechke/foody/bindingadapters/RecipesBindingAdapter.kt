package com.skylabstechke.foody.bindingadapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.skylabstechke.foody.data.room.RecipesEntity
import com.skylabstechke.foody.models.FoodRecipe
import com.skylabstechke.foody.utils.NetworkResult

class RecipesBindingAdapter {
    companion object {

        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun errImageViewVisibility(
            imageView: ImageView,
            apiResponse: NetworkResult<FoodRecipe?>,
            database: List<RecipesEntity>
        ) {

            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            }else if (apiResponse is NetworkResult.Loading){
                imageView.visibility = View.INVISIBLE
            }else if (apiResponse is NetworkResult.Success){
                imageView.visibility = View.INVISIBLE
            }
        }
    }
}