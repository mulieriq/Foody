package com.skylabstechke.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skylabstechke.foody.data.room.FavoriteEntity
import com.skylabstechke.foody.databinding.FavoriteRowLayoutBinding
import com.skylabstechke.foody.models.Result
import com.skylabstechke.foody.ui.fragments.favorities.FavoriteRecipesFragmentDirections
import com.skylabstechke.foody.utils.RecipesDiffUtil
import kotlinx.android.synthetic.main.favorite_row_layout.view.*

class FavoriteRecyclerViewAdapter :
    RecyclerView.Adapter<FavoriteRecyclerViewAdapter.MyViewHolder>() {

    private var favoriteList = emptyList<FavoriteEntity>()

    class MyViewHolder(private var binding: FavoriteRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite: Result) {
            binding.favorite = favorite
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val favoriteRecipe: Result = favoriteList[position].favoriteEntity
        holder.bind(favoriteRecipe)
        val action = FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToDetailsActivity2(favoriteRecipe)
        holder.itemView.fragment_row_layout.setOnClickListener {
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }


    fun setData(newData: List<FavoriteEntity>) {
        val diffUtil = RecipesDiffUtil(favoriteList, newData)
        val diffUtilCalc = DiffUtil.calculateDiff(diffUtil)
        diffUtilCalc.dispatchUpdatesTo(this)
        favoriteList = newData
    }
}