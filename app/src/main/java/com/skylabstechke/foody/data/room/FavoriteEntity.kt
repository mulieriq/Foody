package com.skylabstechke.foody.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.skylabstechke.foody.models.Result
import com.skylabstechke.foody.utils.Constants.Companion.FAVORITE_TABLE

@Entity(tableName = FAVORITE_TABLE)
 class FavoriteEntity(

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var favoriteEntity: Result
 ){
 }