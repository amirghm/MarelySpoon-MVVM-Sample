package com.amirghm.marelyspoon.data.repository

import com.amirghm.marelyspoon.data.source.RecipeDataSource
import kotlinx.coroutines.flow.map

open class RecipeRepositoryImpl constructor(private val recipeDataSource: RecipeDataSource):
    RecipeRepository {
    override suspend fun getRecipes() = recipeDataSource.getItems().map {
        // The Business Logic can be added here
        it
    }
}