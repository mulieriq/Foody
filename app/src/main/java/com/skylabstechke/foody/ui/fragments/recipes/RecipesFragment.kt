package com.skylabstechke.foody.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.skylabstechke.foody.R
import com.skylabstechke.foody.adapters.RecipesRecyclerViewAdapter
import com.skylabstechke.foody.databinding.FragmentRecipesBinding
import com.skylabstechke.foody.utils.NetworkResult
import com.skylabstechke.foody.utils.observeOnce
import com.skylabstechke.foody.viewmodels.MainViewModel
import com.skylabstechke.foody.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private val args by navArgs<RecipesFragmentArgs>()
    private val mAdapter by lazy { RecipesRecyclerViewAdapter() }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

      setHasOptionsMenu(
          true
      )

        _binding = FragmentRecipesBinding.inflate(
            inflater,
            container,
            false
        ) //inflater.inflate(R.layout.fragment_recipes, container, false)
        binding.lifecycleOwner = this
        binding.mainviewmodel = mainViewModel
        setupRecyclerView()
         requestApiData()
        loadFromCache(false)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_bottomSheet)
        }
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.recipe_fragment_menu,menu)

    }

    private fun loadFromCache(error: Boolean?) {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner, { database ->
                if (database.isNotEmpty() && !args.applyButtonClicked) {
                    Log.d("REQUEST DATA ONCE MORE", "Requesting DATA FROM CACHE")
                    mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                } else {
                    Log.d("BUG " ,error.toString())
                    when (error) {
                        true -> {
                            mAdapter.setData(database[0].foodRecipe)
                            hideShimmerEffect()
                        }
                        false->{Log.d("NO LOADING","LOAD FROM CACE")}
                        else -> {
                            requestApiData()
                        }
                    }

                }

            })
        }
    }

    private fun showShimmerEffect() {

        binding.recyclerview.showShimmer()
    }

    private fun hideShimmerEffect() {

        binding.recyclerview.hideShimmer()
    }

    private fun setupRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun requestApiData() {
        val query = mainViewModel.getRecipes(recipesViewModel.applyQueries())

        Log.d("REQUEST DATA", query.toString())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.d("REQUEST DATA SUCCESS", "Requesting DATA SUCCESS")
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadFromCache(true)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}