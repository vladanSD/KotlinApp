package com.example.vladan.firstkotlinapp.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Vladan on 19.12.2017..
 */
@Entity(foreignKeys = arrayOf(ForeignKey(entity = Recipe::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("recipeId"),
        onDelete = CASCADE)))
data class Step(

        @Expose
        var recipeId: Int?,
        @PrimaryKey
        @SerializedName("id")
        @Expose
        val id: Int?,
        @SerializedName("text")
        @Expose
        val text: String?,
        @SerializedName("en_text")
        @Expose
        val enText: String?,
        @SerializedName("seq_num")
        @Expose
        val seqNum: Int?,
        @SerializedName("timer")
        @Expose
        val timer: Int?,
        @SerializedName("timer_name")
        @Expose
        val timerName: String?,
        @SerializedName("image_file_name")
        @Expose
        val imageFileName: String?
)