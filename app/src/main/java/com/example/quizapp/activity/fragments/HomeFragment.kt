package com.example.quizapp.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.quizapp.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        createUiElements(view)
        return view
    }

    private fun createUiElements(view: View) {
        val testYourSkillsButton = view.findViewById<Button>(R.id.test_your_skills_button)
        val readQuestionsButton = view.findViewById<Button>(R.id.read_questions_button)
        val createQuestionButton = view.findViewById<Button>(R.id.create_question_button)

        testYourSkillsButton.setOnClickListener {
            navigateToFragment(LoginFragment())
        }

        readQuestionsButton.setOnClickListener {
            navigateToFragment(ListFragment())
        }

        createQuestionButton.setOnClickListener {
            navigateToFragment(CreateQuestionFragment())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.main_fragment_container, fragment)?.commit()
    }
}