package com.locathor.brzodolokacije.domain.model

data class Comment(
    val commentId: Int=-1,
    val username:String="Stefan",
    val profilePictureUrl: String="",
    val timestamp:Long=System.currentTimeMillis(),
    val comment:String="Very nice post!",
    val isLiked:Boolean=false,
    val likeCount:Int=12
)
