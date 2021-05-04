package com.skylabstechke.foody.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKey {
        val selectedMealType = preferencesKey<String>("mealType")
        val selectedMealTypeId = preferencesKey<Int>("mealTypeId")

        val selectedDietType = preferencesKey<String>("dietType")
        val selectedDietTypeId = preferencesKey<Int>("dietTypeId")


    }

    private val datastore: DataStore<Preferences> = context.createDataStore(
        name = "foodsPreferences"
    )

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        Log.d("SAVING DATA","SAVING DATA")

        datastore.edit { preferences ->
            preferences[PreferenceKey.selectedDietType] = dietType
            preferences[PreferenceKey.selectedDietTypeId] = dietTypeId
            preferences[PreferenceKey.selectedMealType] = mealType
            preferences[PreferenceKey.selectedMealTypeId] = mealTypeId
        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = datastore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }

    }.map { preference ->
        val selectedMealType = preference[PreferenceKey.selectedMealType] ?: "main course"
        val selectedMealTypeId = preference[PreferenceKey.selectedDietTypeId] ?: 0
        val selectedDietType = preference[PreferenceKey.selectedDietType] ?: "gluten free"
        val selectedDieTypeId = preference[PreferenceKey.selectedDietTypeId] ?: 0

        MealAndDietType(
            selectedMealType,
            selectedMealTypeId,
            selectedDietType,
            selectedDieTypeId
        )
    }

}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int

)