package com.skylabstechke.foody.ui.fragments.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.skylabstechke.foody.R
import com.skylabstechke.foody.adapters.RecipesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_recipes.view.*


class RecipesFragment : Fragment() {
    private val mAdapter by lazy { RecipesRecyclerViewAdapter() }

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mView = inflater.inflate(R.layout.fragment_recipes, container, false)
        setupRecyclerView()
        return mView
    }

    private fun showShimmerEffect() {

        mView.recyclerview.showShimmer()
    }

    private fun hideShimmerEffect() {

        mView.recyclerview.hideShimmer()
    }

    private fun setupRecyclerView() {
        mView.recyclerview.adapter = mAdapter
        mView.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }
}