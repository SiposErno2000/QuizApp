package com.example.quizapp.activity.retrofit.accessLayer

import com.example.quizapp.activity.models.Question
import com.example.quizapp.activity.retrofit.Proxy
import io.reactivex.Single

object DataLayer {

    fun getData() : Single<List<Question>> {
        return Single.create { emitter ->
            val response = Proxy.getData()

            if (response == null) {
                emitter.onError(Exception("Response is null!"))
            } else {
                emitter.onSuccess(response.getQuestionList())
            }
        }
    }
}