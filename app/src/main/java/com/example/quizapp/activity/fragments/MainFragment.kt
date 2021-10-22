package com.example.quizapp.activity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.quizapp.R
import com.example.quizapp.activity.models.QuizController
import com.example.quizapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var questionCounter : Int = 0
    private var correctAnswers : Int = 0
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var questions : QuizController
    private lateinit var radioButton1 : RadioButton
    private lateinit var radioButton2 : RadioButton
    private lateinit var radioButton3 : RadioButton
    private lateinit var radioButton4 : RadioButton
    private lateinit var radioGroup : RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        createUIElements(binding)
        return binding.root
    }

    private fun createUIElements(mainBinding: FragmentMainBinding) {
        questions = QuizController()
        questions.addData()
        questions.randomizeQuestions()
        checkNextButtonText()
        addViews()

        mainBinding.nextButton.setOnClickListener {
            if (questionCounter == questions.getQuestions().size-1) {
                questionCounter++
                navigateToFragment(QuizEndFragment(), correctAnswers.toString(), questionCounter.toString())
            } else {
                questionCounter++
                checkNextButtonText()
                changeViewsText()
                //addViews()
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment, correctAnswers : String, totalAnswers : String) {
        val bundle = Bundle()
        bundle.putString("correctAsw",correctAnswers)
        bundle.putString("totalAsw",totalAnswers)
        fragment.arguments = bundle
        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.popBackStackImmediate()
        fragmentManager?.beginTransaction()?.replace(R.id.main_fragment_container, fragment)?.commit()
    }

    fun onBackPressed() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(R.string.alert_message)
        builder.setCancelable(true)
        builder.setPositiveButton(R.string.positive_alert_button) { _, _ -> navigateToFragment(QuizEndFragment(), correctAnswers.toString(), questionCounter.toString()) }
        builder.setNegativeButton(R.string.negative_alert_button) { dialog, _ -> dialog.cancel() }
        builder.create().show()
    }

    @SuppressLint("SetTextI18n")
    private fun checkNextButtonText() {
        if (questionCounter == questions.getQuestions().size-1) {
            binding.nextButton.text = "Submit"
        } else {
            binding.nextButton.text = "Next"
        }
    }

    private fun changeViewsText() {
        var idCounter = 0
        binding.question.text = questions.getQuestions()[questionCounter].question

        radioButton1.text = questions.getQuestions()[questionCounter].answers[idCounter]
        radioButton1.id = idCounter++

        radioButton2.text = questions.getQuestions()[questionCounter].answers[idCounter]
        radioButton2.id = idCounter++

        radioButton3.text = questions.getQuestions()[questionCounter].answers[idCounter]
        radioButton3.id = idCounter++

        radioButton4.text = questions.getQuestions()[questionCounter].answers[idCounter]
        radioButton4.id = idCounter
    }

    @SuppressLint("ResourceType")
    private fun addViews() {
        var idCounter = 0
        val constraintLayout = binding.mainFragment

        binding.question.text = questions.getQuestions()[questionCounter].question

        radioButton1 = RadioButton(requireContext())
        radioButton1.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        radioButton1.text = questions.getQuestions()[questionCounter].answers[idCounter]
        radioButton1.id = idCounter++

        radioButton2 = RadioButton(requireContext())
        radioButton2.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        radioButton2.text = questions.getQuestions()[questionCounter].answers[idCounter]
        radioButton2.id = idCounter++

        radioButton3 = RadioButton(requireContext())
        radioButton3.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        radioButton3.text = questions.getQuestions()[questionCounter].answers[idCounter]
        radioButton3.id = idCounter++

        radioButton4 = RadioButton(requireContext())
        radioButton4.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        radioButton4.text = questions.getQuestions()[questionCounter].answers[idCounter]
        radioButton4.id = idCounter

        radioGroup = RadioGroup(requireContext())
        val params = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        params.setMargins(50, 300,0 ,0)
        radioGroup.layoutParams = params

        radioGroup.addView(radioButton1)
        radioGroup.addView(radioButton2)
        radioGroup.addView(radioButton3)
        radioGroup.addView(radioButton4)

        constraintLayout.addView(radioGroup)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == 0) {
                correctAnswers++
            }
        }
    }
}