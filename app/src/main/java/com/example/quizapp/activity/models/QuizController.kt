package com.example.quizapp.activity.models

class QuizController {
    private val questions : MutableList<Question> = ArrayList()
    lateinit var question : String
    private var list : MutableList<String> = ArrayList()
    private var list1 : MutableList<String> = ArrayList()

    fun randomizeQuestions() {
        questions.shuffle()
    }

    fun addData() {
        question = "Which file extension is used to save Kotlin files."
        list.add(".java")
        list.add(".java")
        list.add(".java")
        list.add(".java")

        val question2 = "Which file extension is used?"
        list1.add(".kot")
        list1.add(".kot")
        list1.add(".kot")
        list1.add(".kot")

        questions.add(Question(question, list))
        questions.add(Question(question2, list1))
    }

    fun getQuestions() : MutableList<Question> {
        return questions
    }
}