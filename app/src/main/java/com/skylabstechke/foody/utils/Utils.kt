package com.skylabstechke.foody.utils

import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Utils @Inject constructor(@ApplicationContext private var context: Context) {
    fun toast(msg: String) = Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

}