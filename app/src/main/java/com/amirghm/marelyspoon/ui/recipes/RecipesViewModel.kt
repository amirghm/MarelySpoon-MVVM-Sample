package com.amirghm.marelyspoon.ui.recipes

import androidx.lifecycle.*
import com.amirghm.grocery.utils.helper.network.ErrorModel
import com.amirghm.marelyspoon.data.model.recipe.RecipeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor() :
    ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    private val refreshList = MutableLiveData<Boolean>().apply { value = true }
    val errorModel = MutableLiveData<ErrorModel?>()
    val recipeList: MutableLiveData<List<RecipeModel>>
        get() = _recipeList as MutableLiveData<List<RecipeModel>>

    private var _recipeList: LiveData<List<RecipeModel>> = refreshList.switchMap {
        isLoading.value = true
        getProducts()
    }

    private fun getProducts():MutableLiveData<List<RecipeModel>>
    {
        return MutableLiveData<List<RecipeModel>>()
    }

    fun refreshList() {
        refreshList.value = true
    }
}