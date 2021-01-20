package com.skylabstechke.foody.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.skylabstechke.foody.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setupActionBarWithNavController(findNavController(R.id.navHostMain))

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostMain).navigateUp() || super.onSupportNavigateUp()
    }
}