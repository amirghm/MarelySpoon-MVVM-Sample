package com.amirghm.marelyspoon.data.repository

import com.amirghm.marelyspoon.Constants.FAKE_EMPTY_MODEL
import com.amirghm.marelyspoon.Constants.FAKE_RESPONSE_NETWORK_MODEL
import com.amirghm.marelyspoon.data.model.responses.RecipeResponseModel
import com.amirghm.marelyspoon.utils.helper.network.ErrorModel
import com.amirghm.marelyspoon.utils.helper.network.ErrorStatus
import com.amirghm.marelyspoon.utils.helper.network.Result
import kotlinx.coroutines.flow.flow

class FakeRecipeRepository : RecipeRepository {
    private var errorCode = 200
    private var errorMessage = "Not Found"
    private var shouldReturnError = false
    private var shouldEmptyModel = false

    fun setReturnCode(code:Int)
    {
        errorCode = code
    }

    fun setErrorMessage(error:String) {
        errorMessage = error
    }

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
        if(shouldReturnError)
            errorCode = 404
    }

    fun sendEmptyModel(value: Boolean)
    {
        shouldEmptyModel = value
    }

    override suspend fun getRecipes() = flow {
        if (shouldReturnError) {
            emit(Result.error<RecipeResponseModel>(ErrorModel(errorMessage, errorCode, ErrorStatus.INVALID_PARAMETERS)))
        } else {
            emit(Result.success(if(shouldEmptyModel) FAKE_EMPTY_MODEL else FAKE_RESPONSE_NETWORK_MODEL))
        }
    }
}