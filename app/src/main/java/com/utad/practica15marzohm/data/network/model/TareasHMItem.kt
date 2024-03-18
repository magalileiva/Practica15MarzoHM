package com.utad.practica15marzohm.data.network.model


import com.google.gson.annotations.SerializedName

data class TareasHMItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("necessary_materials")
    val necessaryMaterials: List<String>,
    @SerializedName("subject")
    val subject: Subject,
    @SerializedName("teacher_name")
    val teacherName: String,
    @SerializedName("title")
    val title: String
)