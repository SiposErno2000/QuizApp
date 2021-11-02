package com.example.quizapp.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.quizapp.R
import com.example.quizapp.activity.cache.Cache
import com.example.quizapp.activity.models.Question

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        createUIElements(view)
        return view
    }

    private fun createUIElements(view: View) {
        val bundle = arguments
        val position = bundle?.getInt("position")

        val question = view.findViewById<TextView>(R.id.detail_question)
        val answer1 = view.findViewById<TextView>(R.id.answer1_detail)
        val answer2 = view.findViewById<TextView>(R.id.answer2_detail)
        val answer3 = view.findViewById<TextView>(R.id.answer3_detail)
        val answer4 = view.findViewById<TextView>(R.id.answer4_detail)

        val currentQuestion : Question = Cache.getQuestion(position!!)

        question.text = currentQuestion.question
        answer1.text = currentQuestion.correctanswer
        answer2.text = currentQuestion.answers[0]
        answer3.text = currentQuestion.answers[1]
        answer4.text = currentQuestion.answers[2]
    }
}