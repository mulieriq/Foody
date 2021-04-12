package com.skylabstechke.foody.ui.fragments.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skylabstechke.foody.R
import com.skylabstechke.foody.adapters.RecipesRecyclerViewAdapter
import com.skylabstechke.foody.utilis.NetworkResult
import com.skylabstechke.foody.viewmodels.MainViewModel
import com.skylabstechke.foody.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipes.view.*

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private val mAdapter by lazy { RecipesRecyclerViewAdapter() }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var mView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mView = inflater.inflate(R.layout.fragment_recipes, container, false)

        setupRecyclerView()
        requestApiData()
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

    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }


}