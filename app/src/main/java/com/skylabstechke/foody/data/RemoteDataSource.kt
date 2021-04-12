package com.skylabstechke.foody.data
import javax.inject.Inject

class RemoteDataSource  @Inject constructor(
    private val foodRecipiesApi: FoodRecipiesApi
) {
}