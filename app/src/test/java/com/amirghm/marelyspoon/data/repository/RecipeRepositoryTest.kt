package com.amirghm.marelyspoon.data.repository

import com.amirghm.marelyspoon.BaseTest
import com.amirghm.marelyspoon.data.source.remote.RecipeApi
import com.amirghm.marelyspoon.data.source.remote.RecipeRemoteDataSourceImpl
import com.amirghm.marelyspoon.utils.helper.network.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

internal class RecipeRepositoryTest : BaseTest() {

    private lateinit var recipeRepositoryImpl: RecipeRepositoryImpl
    private lateinit var remoteDataSourceImpl: RecipeRemoteDataSourceImpl

    @Before
    override fun setup() {
        super.setup()
        remoteDataSourceImpl =
            RecipeRemoteDataSourceImpl(getApiService(RecipeApi::class.java))
        recipeRepositoryImpl = RecipeRepositoryImpl(remoteDataSourceImpl)
    }

    @Test
    fun `get recipe list`() {
        runBlocking {
            val result = recipeRepositoryImpl.getRecipes()
            result.collect {
                MatcherAssert.assertThat(it.status, `is`(Result.Status.SUCCESS))
                MatcherAssert.assertThat(it.data, `is`(notNullValue()))
                MatcherAssert.assertThat(it.data!!.items?.size, `is`(28))
            }
        }
    }

    @Test
    fun `get recipe return result success`() {
        runBlocking {
            val result = recipeRepositoryImpl.getRecipes()
            result.collect {
                MatcherAssert.assertThat(it.status, `is`(Result.Status.SUCCESS))
            }
        }
    }
}