package com.locathor.brzodolokacije.domain.model

import java.util.Date

data class Post(
    var title: String,
    var desc: String,
    var posted: Date,
    var images: List<String>
)
