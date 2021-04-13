package com.skylabstechke.foody.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.skylabstechke.foody.models.FoodRecipe

class RecipesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipesToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

}