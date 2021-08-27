package com.amirghm.marelyspoon.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.amirghm.marelyspoon.utils.extentions.observeOnce
import com.amirghm.marelyspoon.utils.helper.network.ErrorModel
import com.amirghm.marelyspoon.R
import com.amirghm.marelyspoon.data.model.recipe.RecipeModel
import com.amirghm.marelyspoon.databinding.FragmentRecipesBinding
import com.amirghm.marelyspoon.ui.recipes.list.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var adapter: RecipeAdapter
    private lateinit var binding: FragmentRecipesBinding
    private val viewModel by viewModels<RecipesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViews()
        configureViewModel()
    }

    private fun configureViews() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.swipeToRefresh.setColorSchemeResources(R.color.colorAccent)

        setupRecyclerView()

        binding.swipeToRefresh.setOnRefreshListener { viewModel.refreshList() }
        binding.noInternetContainer.noInternetTryAgainButton.setOnClickListener { viewModel.refreshList() }
        binding.emptyStateContainer.emptyStateTryAgainButton.setOnClickListener { viewModel.refreshList() }
    }

    private fun configureViewModel()
    {
        viewModel.recipeList.observe(viewLifecycleOwner,{ handleRecipeData(it) })
        viewModel.errorModel.observeOnce { showError(it) }
    }

    private fun handleRecipeData(recipes: List<RecipeModel>) {
        if(recipes.isEmpty()) {
            showEmptyState()
            hideError()
        }
        else {
            hideEmptyState()
            hideError()
            adapter.updateData(recipes)
        }
    }

    private fun showEmptyState() {
        binding.emptyStateContainer.root.visibility = View.VISIBLE
    }

    private fun hideEmptyState()
    {
        binding.emptyStateContainer.root.visibility = View.GONE
    }

    private fun showError(errorModel: ErrorModel?) {
        if(errorModel!=null)
            binding.noInternetContainer.root.visibility = View.VISIBLE
    }

    private fun hideError()
    {
        binding.noInternetContainer.root.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        binding.recipesRecyclerView.adapter = getAdapter()
        binding.recipesRecyclerView.layoutManager = getLayoutManager()
    }

    private fun getLayoutManager(): GridLayoutManager = GridLayoutManager(context, resources.getInteger(R.integer.recipe_span_size))

    private fun getAdapter(): RecipeAdapter {
        adapter = RecipeAdapter()
        return adapter
    }

}