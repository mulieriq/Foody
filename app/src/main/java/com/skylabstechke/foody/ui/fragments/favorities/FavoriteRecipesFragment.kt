package com.skylabstechke.foody.ui.fragments.favorities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skylabstechke.foody.R

class FavoriteRecipesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       val view =  inflater.inflate(R.layout.fragment_favorite_recipes, container, false)
        setUpRecyclerView(view)
        return view
    }

    private fun setUpRecyclerView(view: View){

    }

}