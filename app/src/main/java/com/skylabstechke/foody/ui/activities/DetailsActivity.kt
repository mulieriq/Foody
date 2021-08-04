package com.skylabstechke.foody.ui.activities

import android.os.Bundle
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
import com.skylabstechke.foody.ui.fragments.details.IngredientsFragment
import com.skylabstechke.foody.ui.fragments.details.InstructionsFragment
import com.skylabstechke.foody.ui.fragments.details.OverviewFragment
import com.skylabstechke.foody.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val args by navArgs<DetailsActivityArgs>()
    private val favoriteViewModel : FavoriteViewModel by viewModels<FavoriteViewModel>()
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
        menuInflater.inflate(R.menu.details_fav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }else if(item.itemId == R.id.detailsFavorite){
            favoriteViewModel.insertFav(args.result)
            Snackbar.make(
                detailsLayput,
                "Success",
                Snackbar.LENGTH_LONG
            ).apply {
                getColor(R.color.red)
            }.show()
        }
        return super.onOptionsItemSelected(item)
    }
}