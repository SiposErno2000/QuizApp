package com.example.quizapp.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.activity.adapter.ListAdapter
import com.example.quizapp.activity.cache.Cache

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        createUIElements(view)
        return view
    }

    private fun createUIElements(view : View) {
        val list = arrayOf("Any Category", "Own Category", "General Knowledge", "Entertainment: Books", "Entertainment: Film", "Entertainment: Music", "Entertainment: Musicals & Theatres", "Entertainment: Television", "Entertainment: Video Games", "Entertainment: Board Games", "Science & Nature", "Science: Computers", "Science: Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Art", "Celebrities", "Animals", "Vehicles")
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val spinner = view.findViewById<Spinner>(R.id.category_spinner)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        spinner.apply {
            val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (list[position] == "Any Category") {
                        val adapter = ListAdapter(Cache.getQuestionList(), requireActivity())
                        recyclerView.adapter = adapter
                    } else {
                        val adapter = ListAdapter(Cache.getQuestionsByCategory(list[position]), requireActivity())
                        recyclerView.adapter = adapter
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }
    }
}