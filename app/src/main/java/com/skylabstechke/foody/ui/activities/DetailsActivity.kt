package com.skylabstechke.foody.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.skylabstechke.foody.R
import com.skylabstechke.foody.adapters.PagerAdapter
import com.skylabstechke.foody.data.room.FavoriteEntity
import com.skylabstechke.foody.ui.fragments.details.IngredientsFragment
import com.skylabstechke.foody.ui.fragments.details.InstructionsFragment
import com.skylabstechke.foody.ui.fragments.details.OverviewFragment
import com.skylabstechke.foody.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val args by navArgs<DetailsActivityArgs>()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private var recipeSaved = false;
   private  var savedRecipeId:Int ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())
        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        //pass data from activity to bundle
        val result = Bundle()
        result.putParcelable("recipeBundle", args.result)

        val adapter = PagerAdapter(
            result,
            fragments,
            titles,
            supportFragmentManager
        )
        viewpager.adapter = adapter
        tabLayout.setupWithViewPager(viewpager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_fav_menu, menu)
        val menuItem = menu?.findItem(R.id.detailsFavorite)
        checkSavedRecipe(menuItem!!)
        return true
    }

    private fun checkSavedRecipe(menuItem: MenuItem) {
        favoriteViewModel.readFavorite.observe(this) {
            try {
                for (savedRecipe in it)  {
                    if (savedRecipe.favoriteEntity.id == args.result.id) {
                        changeColor(menuItem, R.color.red)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                    }
                }

            } catch (e: Exception) {
                Log.d("Details Activity", e.toString())
            }
        }

    }

    private fun removeFromFav(item: MenuItem) {
      val itemId:Int =   savedRecipeId ?: 0
        val favoriteEntity = FavoriteEntity(
            itemId,
            args.result
        )

        favoriteViewModel.deleteFav(favoriteEntity)
        changeColor(item, R.color.white)
        recipeSaved = false
        snackBarPrompt("Removed")
    }

    private fun saveRecipe(item: MenuItem) {
        favoriteViewModel.insertFav(args.result)
        changeColor(item, R.color.red)
        recipeSaved = true
        savedRecipeId = args.result.id
        snackBarPrompt("Successfully saved")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.detailsFavorite && !recipeSaved) {
            saveRecipe(item)
        } else if (item.itemId == R.id.detailsFavorite && recipeSaved) {
            removeFromFav(item)
        }
     return true
    }

    private fun changeColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }

    private fun snackBarPrompt(msg: String) {
        Snackbar.make(
            detailsLayput,
            msg,
            Snackbar.LENGTH_LONG
        ).apply {
            getColor(R.color.red)
        }.show()
    }

}