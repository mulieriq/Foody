package com.skylabstechke.foody.di

import android.content.Context
import androidx.room.Room
import com.skylabstechke.foody.data.room.RecipesDatabase
import com.skylabstechke.foody.utilis.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {


    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        DATABASE_NAME
    )
}