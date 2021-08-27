package com.amirghm.marelyspoon.data.model.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class RecipeResponseModel(
    @SerializedName("items")
    val items: List<Item>? = null,
    @SerializedName("includes")
    val includes: Includes? = null
):Serializable


data class Item(
    @SerializedName("sys")
    val sys: ItemSys? = null,
    @SerializedName("fields")
    val fields: ItemFields? = null,
):Serializable


data class ItemFields(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("photo")
    private val _photo: ContentSys? = null,
    @SerializedName("chef")
    private val _chef: ContentSys? = null,
    @SerializedName("calories")
    val calories: Int?=null,
    @SerializedName("tags")
    private val _tags: List<ContentSys>? = null
):Serializable {
    val tags get() = _tags?.map { it.sys?.id }?: emptyList()
    val chefId get() = _chef?.sys?.id
    val photoId get() = _photo?.sys?.id
}

data class ItemSys(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("contentType")
    val contentType: ContentSys? = null
):Serializable

data class ContentSys(
    @SerializedName("sys")
    val sys: Sys? = null
):Serializable

data class Sys(
    @SerializedName("id")
    val id: String? = null
):Serializable


data class Includes(
    @SerializedName("Asset")
    val assets: List<Asset>? = null
):Serializable


data class Asset(
    @SerializedName("sys")
    private val _sys: AssetSys? = null,
    @SerializedName("fields")
    private val _fields: AssetField? = null,
):Serializable {

    val id get() = _sys?.id
    val url get() = if(_fields?.file?.url!=null)"http:${_fields.file.url}" else ""

    data class AssetSys(
        @SerializedName("id")
        val id: String? = null,
    ):Serializable

    
    data class AssetField(
        @SerializedName("file")
        val file: File? = null
    ):Serializable {
        
        data class File(
            @SerializedName("url")
            val url: String? = null,
        ):Serializable
    }
}

