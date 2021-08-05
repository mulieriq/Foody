package com.skylabstechke.foody.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.skylabstechke.foody.data.Repository
import com.skylabstechke.foody.data.room.FavoriteEntity
import com.skylabstechke.foody.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoriteViewModel @ViewModelInject constructor(
    private var repository: Repository,
) : ViewModel() {
    val readFavorite: LiveData<List<FavoriteEntity>> = repository.localDs.getFav().asLiveData()

    fun insertFav(result: Result) {
        val favoriteEntity = FavoriteEntity(
            0,
            result
        )
        safeInsertFav(favoriteEntity)
    }

    fun deleteFav(result: Result) {
        val favoriteEntity = FavoriteEntity(
            0,
            result
        )
        safeDeleteFav(favoriteEntity)
    }

    fun deleteAllFav() {
        safeDeleteAllFav()
    }

    private fun safeDeleteAllFav() = viewModelScope.launch {
        try {
            repository.localDs.deleteAllFav()

        } catch (e: Exception) {
            Log.d("DELETE ERROR", e.toString())
        }
    }

    private fun safeDeleteFav(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        try {
            repository.localDs.deleteFav(favoriteEntity)

        } catch (e: Exception) {
            Log.d("DELETE ERROR", e.toString())
        }

    }


    private fun safeInsertFav(favoriteEntity: FavoriteEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.localDs.insertFav(favoriteEntity)
            } catch (e: Exception) {
                Log.d("ERROR BOSS", e.toString())
            }
        }


}