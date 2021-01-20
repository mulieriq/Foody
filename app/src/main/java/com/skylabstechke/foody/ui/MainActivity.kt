package com.skylabstechke.foody.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skylabstechke.foody.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appbarconfig = AppBarConfiguration(setOf(
                R.id.recipesFragment,
                R.id.favoriteRecipesFragment,
                R.id.foodJokeFragment
        ))

        setupActionBarWithNavController(findNavController(R.id.navHostMain),appbarconfig)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).apply {
            setupWithNavController(findNavController(R.id.navHostMain))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostMain).navigateUp() || super.onSupportNavigateUp()
    }
}