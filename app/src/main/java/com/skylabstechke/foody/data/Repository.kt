package com.skylabstechke.foody.data

import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    val remoteDs = remoteDataSource
}