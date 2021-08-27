package com.amirghm.marelyspoon.data.source.remote

import com.amirghm.marelyspoon.data.source.RecipeDataSource
import com.amirghm.marelyspoon.utils.helper.network.ResponseHelper
import com.amirghm.marelyspoon.BuildConfig
import javax.inject.Inject

class RecipeRemoteDataSourceImpl @Inject constructor(var recipeApi: RecipeApi) : RecipeDataSource, ResponseHelper() {
    override suspend fun getItems() = getResultAsFlow { recipeApi.getItems(BuildConfig.SPACE_ID,BuildConfig.ENVIRONMENT_ID,BuildConfig.ACCESS_TOKEN) }
}