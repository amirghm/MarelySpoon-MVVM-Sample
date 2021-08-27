package com.amirghm.marelyspoon.ui.recipes

import androidx.lifecycle.*
import com.amirghm.marelyspoon.data.repository.RecipeRepository
import com.amirghm.marelyspoon.utils.helper.network.ErrorModel
import com.amirghm.marelyspoon.utils.helper.network.Result
import com.amirghm.marelyspoon.data.model.recipe.RecipeModel
import com.amirghm.marelyspoon.ui.recipes.mapper.mapItemsResponseToUIList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(var recipeRepository: RecipeRepository)  :
    ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    private val refreshList = MutableLiveData<Boolean>().apply { value = true }
    val errorModel = MutableLiveData<ErrorModel?>()
    val recipeList: MutableLiveData<List<RecipeModel>>
        get() = _recipeList as MutableLiveData<List<RecipeModel>>

    private var _recipeList: LiveData<List<RecipeModel>> = refreshList.switchMap {
        isLoading.value = true
        getRecipes()
    }

    private fun getRecipes() = liveData {
        recipeRepository.getRecipes().collect { result->
            isLoading.value = false
            when (result.status) {
                Result.Status.ERROR -> {
                    errorModel.postValue(result.errorModel)
                }
                Result.Status.SUCCESS -> {
                    emit(mapItemsResponseToUIList(result.data))
                }
            }
        }
    }

    fun refreshList() {
        refreshList.value = true
    }
}