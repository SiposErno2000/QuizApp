package com.example.quizapp.activity.retrofit

import com.example.quizapp.activity.models.Question
import com.example.quizapp.activity.retrofit.models.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("api.php?amount=100&type=multiple")
    fun getData() : Call<DataResponse<Question>>
}