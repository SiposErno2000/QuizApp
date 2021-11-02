package com.example.quizapp.activity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.quizapp.R

class QuizEndFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz_end, container, false)
        createUIElements(view)
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun createUIElements(view : View) {
        val tryAgainButton = view.findViewById<Button>(R.id.try_again_button)
        val result = view.findViewById<TextView>(R.id.result)

        tryAgainButton.setOnClickListener {
            navigateToFragment(QuizStartFragment())
        }

        val bundle = arguments
        val correctAsw = bundle?.getString("correctAsw")
        val totalAsw = bundle?.getString("totalAsw")

        result.text = "$correctAsw/$totalAsw"
    }

    private fun navigateToFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.popBackStackImmediate()
        fragmentManager?.beginTransaction()?.replace(R.id.main_fragment_container, fragment)?.commit()
    }
}