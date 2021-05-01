package com.skylabstechke.foody.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataStoreRepository @Inject constructor(@ApplicationContext private  val context: Context) {

    private object PreferenceKey{
        val selectedMealType = preferencesKey<String>("mealType")
        val selectedMealTypeId  = preferencesKey<Int>("mealTypeId")

        val selectedDietType = preferencesKey<String>("dietType")
        val selectedDietTypeId  = preferencesKey<Int>("dietTypeId")


    }

    private val datastore :DataStore<Preferences> = context.createDataStore(
        name = "foody_preferences"
    )

}

data class MealAndDietType(
val selectedMealType:String,
val selectedMealTypeId : Int,
val selectedDietType:String,
val selectedDietTypeId: Int

)