package com.portfolio.quizzapp.model

data class Quizz(
    var id: String = "",
    var title: String = "",
    var questions: MutableMap<String, Question> = mutableMapOf()
)