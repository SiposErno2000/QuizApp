package com.example.quizapp.activity.cache

import android.util.Log
import com.example.quizapp.activity.models.Question
import com.example.quizapp.activity.retrofit.accessLayer.DataLayer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.random.Random

object Cache {
    private var playerName : String = ""
    private var highScore : String = "0"
    private var questionList : MutableList<Question> = ArrayList()
    private var disposable: Disposable? = null

    fun loadDataCache() {
        disposable = DataLayer.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                saveQuestionList(it)
            }, {
                Log.d("Error", "error:$it")
            })
    }

    fun saveQuestionList(list : List<Question>) {
        questionList.addAll(list)
    }

    fun getPlayerName() : String {
        return playerName
    }

    fun setPlayerName(name : String) {
        playerName = name
    }

    fun getHighScore() : String{
        return highScore
    }

    fun setHighScore(score : String) {
        highScore = score
    }

    fun getQuestionList() : MutableList<Question> {
        return questionList
    }

    fun getTenQuestionRandomly() : MutableList<Question> {
        var counter = 0
        val list : MutableList<Question> = ArrayList()
        while (counter < 10) {
            list.add(getQuestion(Random.nextInt(0, questionList.size - 1)))
            counter++
        }
        return list
    }

    fun getQuestionsByCategory(category : String) : MutableList<Question> {
        val list : MutableList<Question> = ArrayList()
        questionList.forEach {
            if (it.category == category) {
                list.add(it)
            }
        }
        return list
    }

    fun addQuestion(question: Question) {
        questionList.add(question)
    }

    fun getQuestion(position: Int) : Question {
        return questionList[position]
    }

    fun dispose() {
        if (disposable?.isDisposed == true) {
            disposable?.dispose()
        }
    }
}