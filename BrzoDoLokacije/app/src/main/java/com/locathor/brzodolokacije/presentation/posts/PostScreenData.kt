package com.locathor.brzodolokacije.presentation.posts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostScreenData(
    val title: String,
    val desc: String
): Parcelable
