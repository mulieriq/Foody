package com.skylabstechke.foody.di

import com.skylabstechke.foody.data.FoodRecipiesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {



     @Singleton
     @Provides
    fun providesApiService(retrofit:Retrofit):FoodRecipiesApi{
        return retrofit.create(FoodRecipiesApi::class.java)
    }
}