package com.skylabstechke.foody.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(
    private val results: Bundle,
    private val fragments: ArrayList<Fragment>,
    private val title: ArrayList<String>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return title[position]
    }

    override fun getItem(position: Int): Fragment {
        fragments[position].arguments = results
        return fragments[position]
    }
}