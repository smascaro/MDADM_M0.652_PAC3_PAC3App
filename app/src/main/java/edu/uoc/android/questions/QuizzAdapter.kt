package edu.uoc.android.questions

import android.graphics.Typeface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.FitCenter
import edu.uoc.android.R
import edu.uoc.android.common.loadFromUrl

class QuizzAdapter : RecyclerView.Adapter<QuizzAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.question_title)
        val image = view.findViewById<ImageView>(R.id.question_image)
        val answer1 = view.findViewById<TextView>(R.id.question_answer_1)
        val answer2 = view.findViewById<TextView>(R.id.question_answer_2)
    }

    private val items = mutableListOf<QuizzQuestion>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_quizz_question, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = items[position]
        holder.title.text = question.title
        holder.image.loadFromUrl(question.imageUrl, FitCenter())
        holder.answer1.text = question.answer1
        holder.answer2.text = question.answer2
        markRightAnswerAsBold(holder, question)
    }

    private fun markRightAnswerAsBold(holder: ViewHolder, question: QuizzQuestion) {
        val answerTextView = if (question.rightAnswer == 1L) {
            holder.answer1
        } else {
            holder.answer2
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setBoldAttrPostMarshmallow(answerTextView)
        } else {
            setBoldAttrPreMarshmallow(answerTextView)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setBoldAttrPostMarshmallow(textView: TextView) {
        textView.setTextAppearance(R.style.QuizzRightAnswerTextAppearance)
    }

    private fun setBoldAttrPreMarshmallow(textView: TextView) {
        textView.typeface = Typeface.create(textView.typeface, Typeface.BOLD)
    }

    override fun getItemCount() = items.size

    fun bindQuestions(questions: List<QuizzQuestion>) {
        items.clear()
        items.addAll(questions)
        notifyDataSetChanged()
    }

}