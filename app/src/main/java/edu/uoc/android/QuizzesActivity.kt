package edu.uoc.android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class QuizzesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzes)
        initializeRecyclerView()
        CoroutineScope(Dispatchers.IO).launch { loadQuestions() }
    }

    private lateinit var questionsList: RecyclerView


    private fun initializeRecyclerView() {
        questionsList = findViewById(R.id.questions_list)
        questionsList.adapter = QuizzAdapter()
    }

    private suspend fun loadQuestions() {
        try {
            val questions = QuestionsService.getQuestions()
            Timber.i(questions.toString())
            CoroutineScope(Dispatchers.Main).launch {
                (questionsList.adapter as QuizzAdapter).bindQuestions(
                    questions
                )
            }
        } catch (e: Exception) {
            Timber.e(e)
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
}