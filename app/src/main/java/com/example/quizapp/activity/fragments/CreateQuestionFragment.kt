package com.example.quizapp.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.quizapp.R
import com.example.quizapp.activity.cache.Cache
import com.example.quizapp.activity.models.Question
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class CreateQuestionFragment : Fragment() {
    private lateinit var addQuestionButton : Button
    private lateinit var question : TextInputLayout
    private lateinit var correctAnswer : TextInputLayout
    private lateinit var answer2 : TextInputLayout
    private lateinit var answer3 : TextInputLayout
    private lateinit var answer4 : TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_question, container, false)
        createUIElements(view)
        return view
    }

    private fun createUIElements(view: View) {
        addQuestionButton = view.findViewById<Button>(R.id.add_question)
        question = view.findViewById<TextInputLayout>(R.id.question_placeholder)
        correctAnswer = view.findViewById<TextInputLayout>(R.id.correct_answer)
        answer2 = view.findViewById<TextInputLayout>(R.id.answer2)
        answer3 = view.findViewById<TextInputLayout>(R.id.answer3)
        answer4 = view.findViewById<TextInputLayout>(R.id.answer4)

        addQuestionButtonClickListener()
    }

    private fun addQuestionButtonClickListener() {
        addQuestionButton.setOnClickListener {
            if (isNotEmpty(question) && isNotEmpty(correctAnswer) && isNotEmpty(answer2) && isNotEmpty(answer3) && isNotEmpty(answer4)) {
                val answerList : MutableList<String> = ArrayList()
                answerList.add(correctAnswer.editText?.text.toString())
                answerList.add(answer2.editText?.text.toString())
                answerList.add(answer3.editText?.text.toString())
                answerList.add(answer4.editText?.text.toString())
                Cache.addQuestion(Question(question.editText?.text.toString(), answerList))
                navigateToFragment(HomeFragment())
            } else {
                Toast.makeText(requireContext(), "Fields can not be empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isNotEmpty(text : TextInputLayout) : Boolean {
        return text.editText?.text.toString().isNotEmpty()
    }

    private fun navigateToFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.main_fragment_container, fragment)?.commit()
    }
}