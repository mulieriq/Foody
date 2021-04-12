package com.skylabstechke.foody.data.network

import com.skylabstechke.foody.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queryMap: Map<String, String>
    ): Response<FoodRecipe>
}