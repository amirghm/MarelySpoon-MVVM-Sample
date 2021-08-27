package com.amirghm.marelyspoon.view.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amirghm.marelyspoon.utils.MainCoroutineRule
import com.amirghm.marelyspoon.data.repository.FakeRecipeRepository
import com.amirghm.marelyspoon.utils.getOrAwaitValue
import com.amirghm.marelyspoon.utils.observeForTesting
import com.amirghm.marelyspoon.ui.recipes.RecipesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipesViewModelTest {
    private lateinit var recipeViewModel: RecipesViewModel
    private lateinit var fakeRecipeRepository: FakeRecipeRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        fakeRecipeRepository = FakeRecipeRepository()
        recipeViewModel = RecipesViewModel(fakeRecipeRepository)
    }

    @Test
    fun `get recipe from repository and return success`() {
        fakeRecipeRepository.setReturnError(false)

        recipeViewModel.refreshList()

        recipeViewModel.recipeList.observeForTesting {
            MatcherAssert.assertThat(
                recipeViewModel.recipeList.getOrAwaitValue().isEmpty(),
                CoreMatchers.`is`(false)
            )
        }
    }

    @Test
    fun `get recipe from repository with empty size`() {
        fakeRecipeRepository.setReturnError(false)
        fakeRecipeRepository.sendEmptyModel(true)
        recipeViewModel.refreshList()

        recipeViewModel.recipeList.observeForTesting {
            MatcherAssert.assertThat(
                recipeViewModel.recipeList.getOrAwaitValue().isEmpty(),
                CoreMatchers.`is`(true)
            )
        }
    }

    @Test
    fun `get recipe from repository and return error with internal server error message`() {
        fakeRecipeRepository.setReturnError(true)
        fakeRecipeRepository.setErrorMessage("Internal Server Error")

        recipeViewModel.refreshList()

        recipeViewModel.recipeList.observeForTesting {
            MatcherAssert.assertThat(
                recipeViewModel.errorModel.getOrAwaitValue()?.message,
                CoreMatchers.`is`("Internal Server Error")
            )
        }
    }

    @Test
    fun `get recipe from repository and return error with server error`() {
        fakeRecipeRepository.setReturnError(true)
        fakeRecipeRepository.setReturnCode(500)

        recipeViewModel.refreshList()

        recipeViewModel.recipeList.observeForTesting {
            MatcherAssert.assertThat(
                recipeViewModel.errorModel.getOrAwaitValue()?.code,
                CoreMatchers.`is`(500)
            )
        }
    }

    @Test
    fun `get recipe from repository and return error`() {
        fakeRecipeRepository.setReturnError(true)

        recipeViewModel.refreshList()

        recipeViewModel.recipeList.observeForTesting {
            MatcherAssert.assertThat(
                recipeViewModel.errorModel.getOrAwaitValue()?.code,
                CoreMatchers.`is`(404)
            )
        }
    }
}