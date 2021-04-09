package com.skylabstechke.foody.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skylabstechke.foody.R

//Root ui
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//bottom configs
        val appbarconfig = AppBarConfiguration(setOf(
                R.id.recipesFragment,
                R.id.favoriteRecipesFragment,
                R.id.foodJokeFragment
        ))
        //set app bar configs
        setupActionBarWithNavController(findNavController(R.id.navHostMain), appbarconfig)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).apply {
            setupWithNavController(findNavController(R.id.navHostMain))
        }
    }

    //allow navigation
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostMain).navigateUp() || super.onSupportNavigateUp()
    }
}