package com.locathor.brzodolokacije.data.local.post

import androidx.room.ColumnInfo

data class Tuple(
    @ColumnInfo(name="title") val title: String?,
    @ColumnInfo(name="desc") val desc: String?
)
