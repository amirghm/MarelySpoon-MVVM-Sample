package com.amirghm.marelyspoon.ui.recipes.mapper

import com.amirghm.marelyspoon.annotations.ContentType
import com.amirghm.marelyspoon.data.model.recipe.RecipeModel
import com.amirghm.marelyspoon.data.model.responses.Item
import com.amirghm.marelyspoon.data.model.responses.RecipeResponseModel


/**
 * function for RecipeViewModel to convert network response to presentation suitable model
 * */
fun mapItemsResponseToUIList(recipeResponse: RecipeResponseModel?): List<RecipeModel> {
    if (recipeResponse == null) {
        return emptyList()
    }
    return recipeResponse.toRecipeModelList()
}

private fun RecipeResponseModel.toRecipeModelList(): List<RecipeModel> {

    val items = mutableListOf<RecipeModel>()

    if (this.items.isNullOrEmpty()) return emptyList()
    
    val recipeList = this.getRecipeList()

    for (item in recipeList) {
        items.add(
            RecipeModel(
                item.sys?.id ?: "",
                item.fields?.title ?: "",
                getImageUrl(item.fields?.photoId),
                item.fields?.description?:"",
                getChefName(item.fields?.chefId),
                item.fields?.tags?.map { tagId -> getTagName(tagId) }?: emptyList()
            )
        )
    }

    return items
}

private fun RecipeResponseModel.getRecipeList(): List<Item> {
    return this.items?.filter { it.sys?.contentType?.sys?.id == ContentType.CONTENT_TYPE_RECIPE }?:emptyList()
}

private fun RecipeResponseModel.getImageUrl(id: String?): String {
    if (id.isNullOrEmpty()) return ""
    return this.includes?.assets?.firstOrNull { it.id == id }?.url ?: return ""
}

private fun RecipeResponseModel.getChefName(id: String?): String {
    if (id.isNullOrEmpty()) return ""
    return this.items?.firstOrNull { it.sys?.id == id }?.fields?.name ?: ""
}

private fun RecipeResponseModel.getTagName(id: String?): String {
    if (id.isNullOrEmpty()) return ""
    return this.items?.firstOrNull { it.sys?.id == id }?.fields?.name ?: ""
}


