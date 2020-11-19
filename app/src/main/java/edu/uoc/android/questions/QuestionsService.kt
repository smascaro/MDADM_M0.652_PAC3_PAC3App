package edu.uoc.android.questions

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object QuestionsService {
    private const val COLLECTION_QUESTIONS_KEY = "quizz-questions"
    private const val DOCUMENT_KEY_TITLE = "question"
    private const val DOCUMENT_KEY_ANSWER_1 = "answer1"
    private const val DOCUMENT_KEY_ANSWER_2 = "answer2"
    private const val DOCUMENT_KEY_RIGHT_ANSWER = "rightAnswer"
    private const val DOCUMENT_KEY_IMAGE_URL = "imageUrl"
    private const val EXCEPTION_DOCUMENT_PARSE_MESSAGE =
        "This document could not be parsed as a quizz question"
    private val firestore by lazy { FirebaseFirestore.getInstance() }

    suspend fun getQuestions(): List<QuizzQuestion> {
        return suspendCoroutine { cont ->
            try {
                firestore.collection(COLLECTION_QUESTIONS_KEY).get()
                    .addOnSuccessListener { querySnapshot ->
                        try {
                            val questions = querySnapshot.documents.map { it.parseQuestion() }
                            cont.resume(questions)
                        } catch (e: Exception) {
                            cont.resumeWithException(e)
                        }
                    }.addOnFailureListener {
                        cont.resumeWithException(it)
                    }
            } catch (e: Exception) {
                cont.resumeWithException(e)
            }
        }
    }

    private fun DocumentSnapshot.parseQuestion(): QuizzQuestion {
        return QuizzQuestion(
            getString(DOCUMENT_KEY_TITLE)
                ?: throw RuntimeException(EXCEPTION_DOCUMENT_PARSE_MESSAGE),
            getString(DOCUMENT_KEY_ANSWER_1) ?: throw RuntimeException(
                EXCEPTION_DOCUMENT_PARSE_MESSAGE
            ),
            getString(DOCUMENT_KEY_ANSWER_2) ?: throw RuntimeException(
                EXCEPTION_DOCUMENT_PARSE_MESSAGE
            ),
            getLong(DOCUMENT_KEY_RIGHT_ANSWER) ?: throw RuntimeException(
                EXCEPTION_DOCUMENT_PARSE_MESSAGE
            ),
            getString(DOCUMENT_KEY_IMAGE_URL) ?: throw RuntimeException(
                EXCEPTION_DOCUMENT_PARSE_MESSAGE
            )
        )
    }
}