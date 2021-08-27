package com.amirghm.marelyspoon.data.model.recipe

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Holds recipe data and used into the recipes adapter
 * @param id : recipe id
 * @param title : recipe name
 * @param imageUrl : recipe image url
 * @param description : recipe description
 * @param chef : recipe chef name
 * @param tags : recipe tags
 */
@Parcelize
data class RecipeModel(
    val id: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val chef: String,
    val tags: List<String>
): Parcelable
