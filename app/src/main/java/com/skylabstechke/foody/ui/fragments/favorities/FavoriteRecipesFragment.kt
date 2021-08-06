package com.skylabstechke.foody.ui.fragments.favorities

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skylabstechke.foody.R
import com.skylabstechke.foody.adapters.FavoriteRecyclerViewAdapter
import com.skylabstechke.foody.databinding.FragmentFavoriteRecipesBinding
import com.skylabstechke.foody.utils.RecipesDiffUtil
import com.skylabstechke.foody.utils.Utils
import com.skylabstechke.foody.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite_recipes.view.*

@AndroidEntryPoint
class FavoriteRecipesFragment(
) : Fragment() {

    private val favoriteRecyclerView by lazy { FavoriteRecyclerViewAdapter(requireActivity()) }
    private val favoriteViewModel:FavoriteViewModel by viewModels()
    private  var _binding:FragmentFavoriteRecipesBinding?=null
    private val binding get() = _binding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater,container,false)
       //val view =  inflater.inflate(R.layout.fragment_favorite_recipes, container, false)
        binding?.lifecycleOwner = this
        binding?.favoriteViewModel = favoriteViewModel
        setHasOptionsMenu(true)

        setUpRecyclerView()
        readDatabase()
        return binding?.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.detailsFavorite){
            deleteAllFav()
        }
        return true
    }

    private fun deleteAllFav() {
       favoriteViewModel.readFavorite.observe(viewLifecycleOwner){
           if (!it.isNullOrEmpty()){
               favoriteViewModel.deleteAllFav()
           }else{
               favoriteViewModel.toastUtility.toast("No thing to remove")
           }
       }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.details_fav_menu,menu)
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