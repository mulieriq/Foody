package com.skylabstechke.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.skylabstechke.foody.utilis.Constants

class RecipesViewModel(application: Application):AndroidViewModel(application) {
     fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries["number"] = "50"
        queries["apiKey"] = Constants.API_KEY
        queries["type"] = "snack"
        queries["diet"] = "vegan"
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"

        return queries
    }



}