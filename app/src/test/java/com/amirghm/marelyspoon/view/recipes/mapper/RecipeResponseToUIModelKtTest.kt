package com.amirghm.marelyspoon.view.recipes.mapper

import com.amirghm.marelyspoon.BuildConfig
import com.amirghm.marelyspoon.Constants
import com.amirghm.marelyspoon.data.model.recipe.RecipeModel
import com.amirghm.marelyspoon.data.model.responses.RecipeResponseModel
import com.amirghm.marelyspoon.ui.recipes.mapper.mapItemsResponseToUIList
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsInstanceOf
import org.junit.Test

class RecipeResponseToUIModelKtTest {

    @Test
    fun `give recipe network response model and read the data is same`() {
        val uiModel = mapItemsResponseToUIList(Constants.FAKE_RESPONSE_NETWORK_MODEL)
        assertThat(uiModel[0], IsInstanceOf(RecipeModel::class.java))
    }

    @Test
    fun `give recipe network response model while the list is empty`() {
        val uiModel = mapItemsResponseToUIList(RecipeResponseModel())
        assertThat(uiModel.size, `is`(0))
    }

    @Test
    fun `give recipe network response model convert to presentation model return list of RecipeUIModel`() {
        val uiModel = mapItemsResponseToUIList(Constants.FAKE_RESPONSE_NETWORK_MODEL)
        assertThat(uiModel.size, `is`(1))
        assertThat(uiModel[0], IsInstanceOf(RecipeModel::class.java))
    }

}