package com.locathor.brzodolokacije.domain.model


data class Post(
    var title: String,
    var desc: String,
    var latitude: Double,
    var longitude: Double,
    var createdAt: String,
    val image: String?=null,
    val ownerUsername: String,
    val ownerProfilePicture: String?=null,
    val likeCount: Int=0,
    val commentCount: Int=0
)
