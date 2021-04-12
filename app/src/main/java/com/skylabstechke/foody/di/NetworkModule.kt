package com.skylabstechke.foody.di

import com.skylabstechke.foody.data.FoodRecipiesApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    fun providesApiService(retrofit:Retrofit):FoodRecipiesApi{
        return
    }
}