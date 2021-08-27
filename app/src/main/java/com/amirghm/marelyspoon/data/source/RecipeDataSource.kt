package com.amirghm.marelyspoon.data.source

import com.amirghm.marelyspoon.utils.helper.network.Result
import com.amirghm.marelyspoon.data.model.responses.RecipeResponseModel
import kotlinx.coroutines.flow.Flow

interface RecipeDataSource {
    suspend fun getItems(): Flow<Result<RecipeResponseModel?>>
}