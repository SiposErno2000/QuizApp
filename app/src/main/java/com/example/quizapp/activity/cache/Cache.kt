package com.example.quizapp.activity.cache

import com.example.quizapp.activity.models.Question

object Cache {
    private var playerName : String = ""
    private var highScore : String = "0"
    private var questionList : MutableList<Question> = ArrayList()

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

    fun addQuestion(question: Question) {
        questionList.add(question)
    }

    fun addInitialQuestionList(list : MutableList<Question>) {
        list.forEach {
            questionList.add(it)
        }
    }

    fun getQuestion(position: Int) : Question {
        return questionList[position]
    }
}