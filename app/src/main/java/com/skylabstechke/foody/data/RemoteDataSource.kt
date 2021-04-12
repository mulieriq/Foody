package com.skylabstechke.foody.data
import com.skylabstechke.foody.data.network.FoodRecipiesApi
import com.skylabstechke.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource  @Inject constructor(
    private val foodRecipesApi: FoodRecipiesApi
) {
  suspend  fun getRecipes(queries:Map<String,String>) : Response<FoodRecipe>{
      return  foodRecipesApi.getRecipes(queries)
    }
}