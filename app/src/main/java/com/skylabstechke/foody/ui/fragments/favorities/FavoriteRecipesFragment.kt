package com.skylabstechke.foody.ui.fragments.favorities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skylabstechke.foody.R
import com.skylabstechke.foody.adapters.FavoriteRecyclerViewAdapter
import com.skylabstechke.foody.databinding.FragmentFavoriteRecipesBinding
import com.skylabstechke.foody.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite_recipes.view.*

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    private val favoriteRecyclerView by lazy { FavoriteRecyclerViewAdapter() }
    private val favoriteViewModel:FavoriteViewModel by viewModels()
    private  var _binding:FragmentFavoriteRecipesBinding?=null
    private val binding get() = _binding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater,container,false)
       //val view =  inflater.inflate(R.layout.fragment_favorite_recipes, container, false)
        binding?.lifecycleOwner = this
        binding?.favoriteViewModel = favoriteViewModel

        setUpRecyclerView()
        readDatabase()
        return binding?.root
    }

    private fun readDatabase(){
        favoriteViewModel.readFavorite.observe(viewLifecycleOwner){ favEntity->
            favEntity.let {
                favoriteRecyclerView.setData(it)
            }
        }
    }

    private fun setUpRecyclerView(){
        binding?.favoriteRecyclerView?.adapter = favoriteRecyclerView
        binding?.favoriteRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}