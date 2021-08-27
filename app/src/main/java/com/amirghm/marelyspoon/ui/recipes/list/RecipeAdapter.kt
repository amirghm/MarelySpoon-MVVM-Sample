package com.amirghm.marelyspoon.ui.recipes.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amirghm.marelyspoon.data.model.recipe.RecipeModel


class RecipeAdapter : RecyclerView.Adapter<RecipeViewHolder>() {

    private var data: ArrayList<RecipeModel> = ArrayList()

    fun updateData(items: List<RecipeModel>) {
        val diffResult = DiffUtil.calculateDiff(RecipeDiffUtilCallback(this.data, items))
        data.clear()
        data.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeViewHolder.create(parent)
    
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}