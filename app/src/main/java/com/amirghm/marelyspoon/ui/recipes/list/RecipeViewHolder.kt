package com.amirghm.marelyspoon.ui.recipes.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.amirghm.marelyspoon.data.model.recipe.RecipeModel
import com.amirghm.marelyspoon.databinding.ItemRecipeBinding
import com.amirghm.marelyspoon.ui.recipes.RecipesFragmentDirections

class RecipeViewHolder(private val binding: ItemRecipeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RecipeModel) {
        binding.item = item
        binding.setClickListener { onRecipeClicked(it, item) }
    }

    private fun onRecipeClicked(view: View?, item: RecipeModel) {
        val action =
            RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(item)
        view?.findNavController()?.navigate(action)
    }

    companion object {
        fun create(parent: ViewGroup) = RecipeViewHolder(
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}