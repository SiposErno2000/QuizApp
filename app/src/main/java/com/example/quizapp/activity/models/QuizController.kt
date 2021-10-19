package com.example.quizapp.activity.models

import java.io.File
import java.io.InputStream

class QuizController {
    private val questions : MutableList<Question> = ArrayList()
    lateinit var question : String
    private var list : MutableList<String> = ArrayList()
    private var list1 : MutableList<String> = ArrayList()
    private var counter = 1

//    init {
//        val input : InputStream = File("C:\\Users\\ernok\\Desktop\\AndroidProjects\\QuizApp\\app\\questions.txt").inputStream()
//        input.bufferedReader().forEachLine {
//            if (counter == 1) {
//                question = it
//                counter++
//            } else if (counter == 2 || counter == 3 || counter == 4 || counter == 5) {
//                list.add(it)
//                counter++
//
//                if (counter == 6) {
//                    questions.add(Question(question, list))
//                    list = ArrayList()
//                    counter = 1
//                }
//            }
//        }
//    }

    fun randomizeQuestions() {
        questions.shuffle()
    }

    fun addData() {
        question = "Which file extension is used to save Kotlin files."
        list.add(".java")
        list.add(".java")
        list.add(".java")
        list.add(".java")

        val question2 = "Which file extension is used to save Kotlin files."
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