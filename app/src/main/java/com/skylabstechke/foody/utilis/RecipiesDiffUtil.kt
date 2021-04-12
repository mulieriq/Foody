package com.skylabstechke.foody.utilis

import androidx.recyclerview.widget.DiffUtil
import com.skylabstechke.foody.models.Result

class RecipiesDiffUtil(
    private val oldList :List<Result>,
    private val newList : List<Result>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        TODO("Not yet implemented")
    }

    override fun getNewListSize(): Int {
        TODO("Not yet implemented")
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }

}