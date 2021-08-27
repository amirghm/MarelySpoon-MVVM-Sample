package com.amirghm.marelyspoon.data.source.remote

import com.amirghm.marelyspoon.data.model.responses.RecipeResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("/spaces/{spaceId}/environments/{environment}/entries")
    suspend fun getItems(@Path("spaceId") spaceId:String, @Path("environment")environment:String, @Query("access_token") accessToken:String): Response<RecipeResponseModel>
}