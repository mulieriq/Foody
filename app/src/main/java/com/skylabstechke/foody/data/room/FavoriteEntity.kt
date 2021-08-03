package com.skylabstechke.foody.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skylabstechke.foody.models.Result
import com.skylabstechke.foody.utils.Constants.Companion.FAVORITE_TABLE

@Entity(tableName = FAVORITE_TABLE)
 class FavoriteEntity(
    var favoriteEntity: Result
 ){
    @PrimaryKey(autoGenerate = true)
     var id = 0
 }