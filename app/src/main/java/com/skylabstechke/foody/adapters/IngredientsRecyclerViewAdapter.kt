package com.skylabstechke.foody.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skylabstechke.foody.R
import com.skylabstechke.foody.models.Result
import com.skylabstechke.foody.models.ExtendedIngredient
import com.skylabstechke.foody.utils.RecipesDiffUtil
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import kotlinx.android.synthetic.main.placeholder_row.view.*

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
        holder.itemView.textView.text = ingredientsLists[position].name
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