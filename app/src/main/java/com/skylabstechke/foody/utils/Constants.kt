package com.skylabstechke.foody.utils

class Constants {
    companion object {
        const val API_KEY = "3ebfff5f4fd849bea702b5b5df175767"
        const val BASE_URL = "https://api.spoonacular.com"
        private const val IMAGE_SIZE = "ingredients_250x250"
        const val IMAGE_BASE_URL = "https://spoonacular.com/cdn/$IMAGE_SIZE/"

        //API Query Keys
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFO = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"
       //ROOM Database
        const val DATABASE_NAME= "recipes_database"
        const val RECIPES_TABLE = "recipes_table"
        const val FAVORITE_TABLE = "favorite_table"

        //DEFAULT DATA
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = " gulten free"
        //Search
        const val QUERY_SEARCH = "query"

    }
}