package edu.uoc.android.questions

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.android.R
import edu.uoc.android.base.TargetActivity
import edu.uoc.android.common.hide
import edu.uoc.android.common.show
import kotlinx.android.synthetic.main.activity_quizzes.*
import kotlinx.android.synthetic.main.layout_loading_state.*
import kotlinx.coroutines.*
import timber.log.Timber

class QuizzesActivity : TargetActivity() {

    private val ui = CoroutineScope(Dispatchers.Main + Job())
    private val io = CoroutineScope(Dispatchers.IO + Job())

    private lateinit var questionsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzes)
        initializeRecyclerView()
        loadQuestions()
    }

    private fun initializeRecyclerView() {
        questionsList = findViewById(R.id.questions_list)
        questionsList.adapter = QuizzAdapter()
    }

    private fun loadQuestions() = ui.launch {
        try {
            showProgress(true)
            val questions = withContext(io.coroutineContext) { QuestionsService.getQuestions() }
            Timber.i(questions.toString())
            if (questions.isEmpty()) {
                notifyError()
            } else {
                showProgress(false)
                questions_list.show()
                (questionsList.adapter as QuizzAdapter).bindQuestions(
                    questions
                )
            }
        } catch (e: Exception) {
            notifyError()
            Timber.e(e)
            Toast.makeText(this@QuizzesActivity, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun notifyError() {
        error_container.show()
        showErrorComponents()
    }

    private fun showErrorComponents() {
        showProgress(false)
        setErrorImageCallback()
    }

    private fun setErrorImageCallback() {
        ui.launch {
            error_image.setOnClickListener {
                it.setOnClickListener(null)
                error_container.hide()
                loadQuestions()
            }
        }
    }

    private fun showProgress(show: Boolean) {
        when (show) {
            true -> loading_indicator.show()
            false -> loading_indicator.hide()
        }
    }
}