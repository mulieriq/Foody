package com.skylabstechke.foody.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.skylabstechke.foody.R
import com.skylabstechke.foody.adapters.PagerAdapter
import com.skylabstechke.foody.ui.fragments.details.IngredientsFragment
import com.skylabstechke.foody.ui.fragments.details.InstructionsFragment
import com.skylabstechke.foody.ui.fragments.details.OverviewFragment
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private val args by navArgs<DetailsActivityArgs>()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}