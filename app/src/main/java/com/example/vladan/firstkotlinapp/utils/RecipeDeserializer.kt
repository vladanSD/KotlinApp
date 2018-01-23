package com.example.vladan.firstkotlinapp.utils

import com.example.vladan.firstkotlinapp.data.model.Recipe
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


/**
 * Created by Vladan on 30.11.2017..
 */

class RecipeDeserializer : JsonDeserializer<Recipe> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext?): Recipe {
        return Recipe.napraviParsiranObjekat(json)
    }
}
