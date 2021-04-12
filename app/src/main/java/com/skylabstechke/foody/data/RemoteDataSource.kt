package com.skylabstechke.foody.data
import com.skylabstechke.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource  @Inject constructor(
    private val foodRecipiesApi: FoodRecipiesApi
) {
  suspend  fun getRecipes(queries:Map<String,String>) : Response<FoodRecipe>{
      return  foodRecipiesApi.getRecipes(queries)
    }
}