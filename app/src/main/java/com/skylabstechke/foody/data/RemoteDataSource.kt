package com.skylabstechke.foody.data

import com.skylabstechke.foody.data.network.FoodRecipesApi
import com.skylabstechke.foody.models.FoodRecipe
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }
    suspend fun searchRecipes(queries: Map<String, String>):Response<FoodRecipe>{
        return foodRecipesApi.searchRecipe(queries)
    }

}