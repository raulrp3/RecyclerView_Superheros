package com.example.recyclerviewapplication.Models

import com.google.gson.Gson

data class Superhero (
    var superhero: String,
    var publisher: String,
    var realName: String,
    var image: String
) {
    public fun toJson(): String {
        val gson: Gson = Gson();
        val json: String = gson.toJson(this);
        return json;
    }

    public fun fromJson(json: String): Superhero {
        val gson: Gson = Gson();
        return  gson.fromJson(json, Superhero::class.java);
    }
}