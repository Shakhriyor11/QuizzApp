package com.portfolio.quizzapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.portfolio.quizzapp.R
import com.portfolio.quizzapp.fragments.QuestionFragment
import com.portfolio.quizzapp.model.Quizz
import com.portfolio.quizzapp.utils.ColorPicker
import com.portfolio.quizzapp.utils.IconPicker

class QuizzAdapter(val context: Context, val quizzes: List<Quizz>) :
    RecyclerView.Adapter<QuizzAdapter.QuizzViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizzViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false)
        return QuizzViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizzViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener {
            Toast.makeText(context, quizzes[position].title, Toast.LENGTH_SHORT).show()
//            val intent = Intent(context, QuestionActivity::class.java)
//            intent.putExtra("DATE", quizzes[position].title)
//            context.startActivity(intent)
            val fragment = QuestionFragment()
            val bundle = Bundle()
            bundle.putString("DATE", quizzes[position].title)
            fragment.arguments = bundle

            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    inner class QuizzViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }
}