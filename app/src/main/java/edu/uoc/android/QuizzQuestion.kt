package edu.uoc.android

data class QuizzQuestion(
    val title: String,
    val answer1: String,
    val answer2: String,
    val rightAnswer: Long,
    val imageUrl: String
)