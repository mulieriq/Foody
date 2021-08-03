package com.skylabstechke.foody.data

import com.skylabstechke.foody.data.room.FavoriteEntity
import com.skylabstechke.foody.data.room.RecipesDao
import com.skylabstechke.foody.data.room.RecipesEntity
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {
    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    fun getFav(): Flow<List<FavoriteEntity>> {
        return recipesDao.readFavorite()
    }

    suspend fun insertFav(favoriteEntity: FavoriteEntity) {
        return recipesDao.insertFavorites(favoriteEntity)
    }

    suspend fun deleteFav(favoriteEntity: FavoriteEntity) {
        return recipesDao.deleteFavorite(favoriteEntity)
    }

    suspend fun deleteAllFav(favoriteEntity: FavoriteEntity) {
        return recipesDao.deleteAllFav()
    }
}