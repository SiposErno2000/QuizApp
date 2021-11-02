package com.example.quizapp.activity.retrofit.models

import com.example.quizapp.activity.models.Question
import com.google.gson.annotations.SerializedName

class DataResponse<T> {
    @SerializedName("results")
    private lateinit var questionList : MutableList<Question>

    fun getQuestionList() : List<Question> {
        return questionList
    }
}