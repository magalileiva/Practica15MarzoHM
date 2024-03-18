package com.utad.practica15marzohm.data.network.model


import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("class")
    val classX: String,
    @SerializedName("title")
    val title: String
)