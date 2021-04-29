package com.skylabstechke.foody.data

import android.content.Context
import androidx.datastore.preferences.core.preferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataStoreRepository @Inject constructor(@ApplicationContext private  val context: Context) {

    private object PreferenceKey{
        val selectedMealType = preferencesKey<String>("mealType")
        val selectedMealTypeId  = preferencesKey<Int>("mealTypeId")

        val selectedDietType = preferencesKey<String>("dietType")
        val selectedDietTypeId  = preferencesKey<Int>("dietTypeId")


    }

}