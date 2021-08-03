package com.skylabstechke.foody.ui.fragments.favorities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skylabstechke.foody.R
import com.skylabstechke.foody.adapters.FavoriteRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_favorite_recipes.view.*

class FavoriteRecipesFragment : Fragment() {

    private val favoriteRecyclerView by lazy { FavoriteRecyclerViewAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       val view =  inflater.inflate(R.layout.fragment_favorite_recipes, container, false)
        setUpRecyclerView(view)
        return view
    }

    private fun setUpRecyclerView(view: View){
        view.favoriteRecyclerView.adapter = favoriteRecyclerView
        view.favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}