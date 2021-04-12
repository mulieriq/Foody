package com.skylabstechke.foody.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import com.skylabstechke.foody.data.Repository

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,

    application: Application
) : AndroidViewModel(application) {
}