package com.skylabstechke.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.skylabstechke.foody.utils.Constants.Companion.API_KEY
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_ADD_RECIPE_INFO
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_API_KEY
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_DIET
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_NUMBER
import com.skylabstechke.foody.utils.Constants.Companion.QUERY_TYPE


class RecipesViewModel(application: Application) : AndroidViewModel(application) {
    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = "main course"
        queries[QUERY_DIET] = "glutten free"
        queries[QUERY_ADD_RECIPE_INFO] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }


}