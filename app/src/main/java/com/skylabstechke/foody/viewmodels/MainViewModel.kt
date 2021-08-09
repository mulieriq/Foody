package com.skylabstechke.foody.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.skylabstechke.foody.data.DataStoreRepository
import com.skylabstechke.foody.data.Repository
import com.skylabstechke.foody.data.room.RecipesEntity
import com.skylabstechke.foody.models.FoodRecipe
import com.skylabstechke.foody.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,

    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    /**ROOM*/
    val readRecipes: LiveData<List<RecipesEntity>> = repository.localDs.readDatabase().asLiveData()
    private fun insertRecipes(recipesEntity: RecipesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.localDs.insertRecipes(recipesEntity)
                Log.d("LOCAL", "SAVED")
            } catch (e: Exception) {
                Log.d("INSERT", e.toString().toLowerCase(Locale.ROOT))
            }
        }

    /**RETROFIT*/

    var recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    var searchResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    fun searchRecipes(queries: Map<String, String>) = viewModelScope.launch {
        searchRecipesSafecall(queries)
    }

    private suspend fun searchRecipesSafecall(queries: Map<String, String>) {
        searchResponse.value = NetworkResult.Loading()

        Log.d("Search", "Search API")


        try {
            Log.d("API", "CALLED")

            val response = repository.remoteDs.searchRecipes(queries)
            recipesResponse.value = handleFoodRecipesResponse(response)

        } catch (e: Exception) {
            recipesResponse.value = NetworkResult.Error("Recipes not found.")
        }


    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()

        try {
            Log.d("API", "CALLED")
            val response = repository.remoteDs.getRecipes(queries)
            Log.d("API RESPONSE CALL", response.body().toString())
            recipesResponse.value = handleFoodRecipesResponse(response)
            //offline cache
            val foodRecipe = recipesResponse.value!!.data
            Log.d("API", foodRecipe.toString())
            if (foodRecipe != null) {
                offlineCacheRecipe(foodRecipe)
            }

        } catch (e: Exception) {
            NetworkResult.Error(e.toString(), data = null)
            Log.d("RECIPE", "AN error occured $e")
        }


    }

    private fun offlineCacheRecipe(foodRecipe: FoodRecipe) {
        val recipesEntity = RecipesEntity(foodRecipe)
        insertRecipes(recipesEntity)

    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        Log.d("API RESPONSE", response.body().toString())
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }

            response.code() == 402 -> {
                return NetworkResult.Error("API key limited.")
            }

            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)

            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }

    }

}