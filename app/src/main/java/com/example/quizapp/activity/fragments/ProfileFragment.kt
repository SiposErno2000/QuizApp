package com.example.quizapp.activity.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.quizapp.R
import com.example.quizapp.activity.cache.Cache
import android.content.DialogInterface

import android.widget.EditText
import android.widget.ImageView


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        createUIElements(view)
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun createUIElements(view: View) {
        val name = view.findViewById<TextView>(R.id.name)
        val score = view.findViewById<TextView>(R.id.score)
        val editButton = view.findViewById<ImageView>(R.id.edit_icon)

        if (Cache.getPlayerName() != "") {
            name.text = Cache.getPlayerName()
        }

        if (Cache.getHighScore() != "0") {
            score.text = Cache.getHighScore() + "points"
        }

        editButton.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            val input = EditText(requireContext())
            alert.setView(input)
            alert.setNegativeButton("Cancel") { dialog, which -> }
            alert.setPositiveButton("OK") { dialog, which ->
                if (input.text.toString() != "") {
                    Cache.setPlayerName(input.text.toString())
                    name.text = input.text.toString()
                }
            }
            alert.show()
        }
    }
}