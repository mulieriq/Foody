package com.skylabstechke.foody.viewmodels

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.skylabstechke.foody.data.DataStoreRepository
import com.skylabstechke.foody.utils.Constants.Companion.API_KEY
import com.skylabstechke.foody.utils.Constants.Companion.DEFAULT_DIET_TYPE
import com.skylabstechke.foody.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_ADD_RECIPE_INFO
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_API_KEY
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_DIET
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_NUMBER
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RecipesViewModel @ViewModelInject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {
    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

     val readMealDietType = dataStoreRepository.readMealAndDietType

    fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataStoreRepository.saveMealAndDietType(
                    mealType,
                    mealTypeId,
                    dietType,
                    dietTypeId
                )
            }catch (e:Exception){
                Log.d("REPOSITORY",e.toString())
            }

        }

    }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readMealDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }
        Log.d("QUERY " ,mealType)
        Log.d("QUERY " ,dietType)
        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFO] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }


}