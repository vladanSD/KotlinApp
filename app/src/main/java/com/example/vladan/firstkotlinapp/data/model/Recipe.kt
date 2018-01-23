package com.example.vladan.firstkotlinapp.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Vladan on 11.12.2017..
 */
@Entity(tableName = "recipes")
data class Recipe(
        @PrimaryKey
        @SerializedName("id")
        @Expose
        val id: Int?,
        @SerializedName("author_id")
        @Expose
        val authorId: Int?,
        @SerializedName("title")
        @Expose
        val title: String?,
        @SerializedName("en_title")
        @Expose
        val enTitle: String?,
        @SerializedName("image_file_name")
        @Expose
        val imageFileName: String?,
        @SerializedName("video")
        @Expose
        val video: String?,
        @SerializedName("image_size")
        @Expose
        val imageSize: String?,
        @SerializedName("difficulty")
        @Expose
        val difficulty: Int?,
        @SerializedName("default_serving_size")
        @Expose
        val defaultServingSize: Int?,
        @SerializedName("default_calories")
        @Expose
        val defaultCalories: String?,
        @SerializedName("preparation_time")
        @Expose
        val preparationTime: Int?,
        @SerializedName("utensils")
        @Expose
        val utensils: String?,
        @SerializedName("en_utensils")
        @Expose
        val enUtensils: String?,
        @SerializedName("description")
        @Expose
        val description: String?,
        @SerializedName("en_description")
        @Expose
        val enDescription: String?,
        @SerializedName("blog_id")
        @Expose
        val blogId: Int?,
        @SerializedName("post_id")
        @Expose
        val postId: Int?,
        @SerializedName("post_url")
        @Expose
        val postUrl: String?,
        @SerializedName("likes")
        @Expose
        val likes: Int?,
        @SerializedName("is_featured")
        @Expose
        val isFeatured: Int?,
        @SerializedName("is_deleted")
        @Expose
        val isDeleted: Int?,
        @SerializedName("created_at")
        @Expose
        val createdAt: Date?,
        @SerializedName("updated_at")
        @Expose
        val updatedAt: Date?,
        @SerializedName("country_id")
        @Expose
        val countryId: Int?,
        @SerializedName("user_id")
        @Expose
        val userId: Int?,
        @SerializedName("is_approved")
        @Expose
        val isApproved: Int?,
        @SerializedName("is_recipeOfDay")
        @Expose
        val isRecipeOfDay: Int?,
        @SerializedName("decline_comment")
        @Expose
        val declineComment: String?) {

    var indexInResponse: Int = -1

    companion object {

        fun srediDate(stringdate: String?): Date {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return sdf.parse(stringdate)
        }

        fun proveriString(jsonEle: JsonElement): String? {
            var string: String? = null
            if (!(jsonEle.isJsonNull)) {
                string = jsonEle.asString
            }
            return string
        }

        fun proveriInt(jsonEle: JsonElement): Int? {
            var int: Int? = null
            if (!(jsonEle.isJsonNull)) {
                int = jsonEle.asInt
            }
            return int
        }

        fun napraviParsiranObjekat(json: JsonElement): Recipe = Recipe(
                proveriInt(json.asJsonObject.get("id")),
                proveriInt(json.asJsonObject.get("author_id")),
                proveriString(json.asJsonObject.get("title")),
                proveriString(json.asJsonObject.get("en_title")),
                "https://api.tastly.net/v2/recipes/image/webp/1080/" + proveriString(json.asJsonObject.get("id")),
                proveriString(json.asJsonObject.get("video")),
                proveriString(json.asJsonObject.get("image_size")),
                proveriInt(json.asJsonObject.get("difficulty")),
                proveriInt(json.asJsonObject.get("default_serving_size")),
                proveriString(json.asJsonObject.get("default_calories")),
                proveriInt(json.asJsonObject.get("preparation_time")),
                proveriString(json.asJsonObject.get("utensils")),
                proveriString(json.asJsonObject.get("en_utensils")),
                proveriString(json.asJsonObject.get("description")),
                proveriString(json.asJsonObject.get("en_description")),
                proveriInt(json.asJsonObject.get("blog_id")),
                proveriInt(json.asJsonObject.get("post_id")),
                proveriString(json.asJsonObject.get("post_url")),
                proveriInt(json.asJsonObject.get("likes")),
                proveriInt(json.asJsonObject.get("is_featured")),
                proveriInt(json.asJsonObject.get("is_deleted")),
                srediDate(proveriString(json.asJsonObject.get("created_at"))),
                srediDate(proveriString(json.asJsonObject.get("updated_at"))),
                proveriInt(json.asJsonObject.get("country_id")),
                proveriInt(json.asJsonObject.get("user_id")),
                proveriInt(json.asJsonObject.get("is_approved")),
                proveriInt(json.asJsonObject.get("is_recipeOfDay")),
                proveriString(json.asJsonObject.get("decline_comment")))
    }


}


