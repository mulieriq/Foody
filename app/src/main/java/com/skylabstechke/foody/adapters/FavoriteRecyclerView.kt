package com.skylabstechke.foody.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skylabstechke.foody.data.room.FavoriteEntity
import com.skylabstechke.foody.databinding.FavoriteRowLayoutBinding
import com.skylabstechke.foody.utils.RecipesDiffUtil

class FavoriteRecyclerView : RecyclerView.Adapter<FavoriteRecyclerView.MyViewHolder>() {

    private var favoriteList = emptyList<FavoriteEntity>()

    class MyViewHolder(private var binding:FavoriteRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(){

        }

        companion object{
            fun from(){

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }


    fun setData(newData:List<FavoriteEntity>){

        val diffUtil = RecipesDiffUtil(favoriteList,newData)
        val diffUtilCalc = DiffUtil.calculateDiff(diffUtil)
        diffUtilCalc.dispatchUpdatesTo(this)
        favoriteList = newData
    }
}