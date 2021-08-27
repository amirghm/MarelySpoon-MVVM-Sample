package com.amirghm.marelyspoon.data.source.remote

import com.amirghm.marelyspoon.BaseTest
import com.amirghm.marelyspoon.utils.helper.network.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

internal class RemoteRecipeDataSourceTest : BaseTest() {
    private lateinit var remoteDataSourceImpl: RecipeRemoteDataSourceImpl

    @Before
    override fun setup() {
        super.setup()
        remoteDataSourceImpl = RecipeRemoteDataSourceImpl(getApiService(RecipeApi::class.java))
    }

    @Test
    fun `get recipe return list of recipe`() {
        runBlocking {
            val result = remoteDataSourceImpl.getItems()
            result.collect {
                MatcherAssert.assertThat(
                    it.status,
                    `is`(Result.Status.SUCCESS)
                )
                MatcherAssert.assertThat(it.data, `is`(notNullValue()))
                MatcherAssert.assertThat(it.data!!.items?.size, `is`(28))
            }
        }
    }

    @Test
    fun `get recipe return success with null error model`() {
        runBlocking {
            val result = remoteDataSourceImpl.getItems()
            result.collect {
                MatcherAssert.assertThat(
                    it.errorModel,
                    `is`(nullValue())
                )
            }
        }
    }

    @Test
    fun `get recipe return result success`() {
        runBlocking {
            val result = remoteDataSourceImpl.getItems()
            result.collect {
                MatcherAssert.assertThat(
                    it.status,
                    `is`(Result.Status.SUCCESS)
                )
            }
        }
    }
}