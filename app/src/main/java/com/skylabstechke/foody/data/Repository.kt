package com.skylabstechke.foody.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val  localDataSource: LocalDataSource
) {
    val remoteDs = remoteDataSource
    val localDs = localDataSource
}