package com.amirghm.marelyspoon.ui.recipes.list

import androidx.recyclerview.widget.DiffUtil
import com.amirghm.marelyspoon.data.model.recipe.RecipeModel

class RecipeDiffUtilCallback(private val oldList:List<RecipeModel>, private val newList:List<RecipeModel>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return  newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[oldItemPosition]

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[oldItemPosition]

        return oldItem.id == newItem.id && oldItem.title == newItem.title
    }

}