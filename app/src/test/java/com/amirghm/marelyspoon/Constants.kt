package com.amirghm.marelyspoon

import com.amirghm.marelyspoon.data.model.recipe.RecipeModel
import com.amirghm.marelyspoon.data.model.responses.*


internal object Constants {

    val FAKE_RESPONSE_NETWORK_MODEL = RecipeResponseModel(
        items = listOf(
            Item(
                sys = ItemSys(
                    contentType = ContentSys(sys = Sys(id = "recipe")),
                    id = null
                ),
                fields = ItemFields(
                    name = null,
                    title = "Good Recipe",
                    description = "Very Good Description",
                    _photo = ContentSys(sys = Sys(id = "1")),
                )
            )
        ),
        includes = Includes(emptyList())
    )
    val FAKE_RECIPE_MODEL =
        RecipeModel("422", "Milk", "/Milk.jpg", "Very Good", "Amir", emptyList())
    val FAKE_EMPTY_MODEL = RecipeResponseModel()
}