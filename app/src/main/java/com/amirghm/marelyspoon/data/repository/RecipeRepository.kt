package com.amirghm.marelyspoon.data.repository

import com.amirghm.marelyspoon.utils.helper.network.Result
import com.amirghm.marelyspoon.data.model.responses.RecipeResponseModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(): Flow<Result<RecipeResponseModel?>>
}