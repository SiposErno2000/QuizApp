package com.example.quizapp.activity.models

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("category")
    val category: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("difficulty")
    val difficulty : String,
    @SerializedName("question")
    val question : String,
    @SerializedName("correct_answer")
    val correctanswer : String,
    @SerializedName("incorrect_answers")
    val answers : List<String>
    )