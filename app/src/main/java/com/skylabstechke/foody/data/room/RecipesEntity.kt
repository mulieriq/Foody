package com.skylabstechke.foody.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skylabstechke.foody.models.FoodRecipe
import com.skylabstechke.foody.utilis.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}