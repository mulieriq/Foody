package com.skylabstechke.foody.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.skylabstechke.foody.R
import com.skylabstechke.foody.models.ExtendedIngredient
import com.skylabstechke.foody.utils.Constants.Companion.IMAGE_BASE_URL
import com.skylabstechke.foody.utils.RecipesDiffUtil
import kotlinx.android.synthetic.main.ingredients_row_layout.view.*

class IngredientsRecyclerViewAdapter :
    RecyclerView.Adapter<IngredientsRecyclerViewAdapter.MyViewHolder>() {

    private var ingredientsLists = emptyList<ExtendedIngredient>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return MyViewHolder(
                    layoutInflater.inflate(
                        R.layout.ingredients_row_layout,
                        parent,
                        false
                    )
                )

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ingredientTitle.text = ingredientsLists[position].name
        holder.itemView.ingredientAmount.text = ingredientsLists[position].amount.toString()
        holder.itemView.ingredientOriginal.text = ingredientsLists[position].original
        holder.itemView.ingredientConsistency.text = ingredientsLists[position].consistency
        holder.itemView.ingredientUnit.text = ingredientsLists[position].unit
        holder.itemView.ingredientImage.load(
            IMAGE_BASE_URL + ingredientsLists[position].image
        ) {
            error( R.drawable.ic_error_placeholder)
            crossfade(600)
            placeholder(R.drawable.ic_error_placeholder)
        }

    }

    override fun getItemCount(): Int {
        return ingredientsLists.size
    }

    fun setData(newData: List<ExtendedIngredient>) {
        val difUtil = RecipesDiffUtil(ingredientsLists, newData)
        val diffUtilCal = DiffUtil.calculateDiff(difUtil)
        diffUtilCal.dispatchUpdatesTo(this)

        ingredientsLists = newData

    }
}