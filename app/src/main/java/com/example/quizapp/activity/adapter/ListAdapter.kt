package com.example.quizapp.activity.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.activity.cache.Cache
import com.example.quizapp.activity.fragments.DetailFragment
import com.example.quizapp.activity.fragments.HomeFragment
import com.example.quizapp.activity.models.Question

class ListAdapter(list: MutableList<Question>, activity : Activity) : RecyclerView.Adapter<ListAdapter.ListHolder>() {
    private var questionList : MutableList<Question> = ArrayList()
    private var activity : Activity

    init {
        questionList = list
        this.activity = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.question.text = questionList[position].question
        holder.firstAnswer.text = questionList[position].answers[0]

        holder.deleteButton.setOnClickListener { deleteItem(position) }
        holder.detailButton.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putInt("position",position)
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment).commit()
        }
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    inner class ListHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val question : TextView
        val firstAnswer : TextView
        val deleteButton : Button
        val detailButton : Button

        init {
            question = itemView.findViewById(R.id.list_question)
            firstAnswer = itemView.findViewById(R.id.first_answer)
            deleteButton = itemView.findViewById(R.id.delete_button)
            detailButton = itemView.findViewById(R.id.detail_button)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteItem(position: Int) {
        questionList.removeAt(position)
        notifyDataSetChanged()
    }
}