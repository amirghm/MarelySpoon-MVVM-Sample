package com.amirghm.marelyspoon.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amirghm.marelyspoon.databinding.FragmentRecipeDetailsBinding

/**
 * This fragment is responsible for showing recipe details
 */
class RecipeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailsBinding
    private val viewModel by viewModels<RecipeDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readArguments()
        configureViews()
    }

    private fun readArguments() {
        val args: RecipeDetailsFragmentArgs by navArgs()
        viewModel.recipeModel = args.recipe
    }


    private fun configureViews() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recipe = viewModel.recipeModel
    }
}