package com.skylabstechke.foody.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.skylabstechke.foody.models.FoodRecipe
import com.skylabstechke.foody.models.Result

class RecipesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipesToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipes(data: String): FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }

     @TypeConverter
     fun resultToString(result: Result) : String{
         return gson.toJson(result)
     }

    @TypeConverter

    fun stringToResult(data: String):Result{
        val listType = object  : TypeToken<Result>(){}.type
        return  gson.fromJson(data,listType)

    }
}