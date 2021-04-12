package com.skylabstechke.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skylabstechke.foody.databinding.RecipeRowLayoutBinding
import com.skylabstechke.foody.models.FoodRecipe
import com.skylabstechke.foody.models.Result

class RecipesRecyclerViewAdapter : RecyclerView.Adapter<RecipesRecyclerViewAdapter.MyViewHolder>() {

    private var recipe = emptyList<com.skylabstechke.foody.models.Result>()

    class MyViewHolder(private val binding: RecipeRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = recipe[position]
        holder.bind(currentResult)
    }

    override fun getItemCount(): Int {
        return recipe.size
    }

    fun setData(newData: FoodRecipe) {
        recipe = newData.results
        notifyDataSetChanged()
    }
}