package com.skylabstechke.foody.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {


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
        if (hasInternetConnection()) {
            Log.d("Search", "Search API")

            if (hasInternetConnection()) {
                try {
                    Log.d("API", "CALLED")

                    val response = repository.remoteDs.searchRecipes(queries)
                    recipesResponse.value = handleFoodRecipesResponse(response)


                } catch (e: Exception) {
                    recipesResponse.value = NetworkResult.Error("Recipes not found.")
                }

            } else {
                recipesResponse.value = NetworkResult.Error("No Internet Connection")
            }

        } else {
            recipesResponse.value = NetworkResult.Error(message = "No Internet Connection")
        }

    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                Log.d("API", "CALLED")

                val response = repository.remoteDs.getRecipes(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)
                //offline cache

                val foodRecipe = recipesResponse.value!!.data
                Log.d("API", foodRecipe.toString())
                if (foodRecipe != null) {
                    offlineCacheRecipe(foodRecipe)
                }


            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error("Recipes not found.")
            }

        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private fun offlineCacheRecipe(foodRecipe: FoodRecipe) {
        Log.d("SAVEME", foodRecipe.toString());
        val recipesEntity = RecipesEntity(foodRecipe)
        insertRecipes(recipesEntity)

    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
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

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}