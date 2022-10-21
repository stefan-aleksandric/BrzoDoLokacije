package com.locathor.brzodolokacije.domain.model

import java.util.Date

data class Post(
    var title: String,
    var desc: String,
    var latitude: Double,
    var longitude: Double,
    var createdAt: String
)
