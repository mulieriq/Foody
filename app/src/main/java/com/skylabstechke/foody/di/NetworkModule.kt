package com.skylabstechke.foody.di

import com.skylabstechke.foody.data.FoodRecipiesApi
import com.skylabstechke.foody.utilis.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    fun providesRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()

    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): FoodRecipiesApi {
        return retrofit.create(FoodRecipiesApi::class.java)
    }
}