package com.amirghm.marelyspoon.data.model.recipe

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Holds tag data and used into the recipe object
 * @param id : tag id
 * @param name : tag name
 */
@Parcelize
data class TagModel(
    val id: String,
    val name: String,
): Parcelable {
}
