package com.amirghm.marelyspoon.di

import com.amirghm.marelyspoon.data.repository.RecipeRepository
import com.amirghm.marelyspoon.data.repository.RecipeRepositoryImpl
import com.amirghm.marelyspoon.data.source.RecipeDataSource
import com.amirghm.marelyspoon.data.source.remote.RecipeApi
import com.amirghm.marelyspoon.data.source.remote.RecipeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteRecipeDataSource

    @RemoteRecipeDataSource
    @Provides
    fun provideRemoteRecipeDataSource(@NetworkModule.Recipe recipeApiService: RecipeApi): RecipeDataSource =
        RecipeRemoteDataSourceImpl(recipeApiService)

    @Provides
    fun provideRecipeRepository(@RemoteRecipeDataSource remoteRecipeDataSource: RecipeDataSource): RecipeRepository =
        RecipeRepositoryImpl(remoteRecipeDataSource)

    @Singleton
    @NetworkModule.Recipe
    @Provides
    fun provideRecipeApiService(retrofit: Retrofit): RecipeApi =
        retrofit.create(RecipeApi::class.java)
}