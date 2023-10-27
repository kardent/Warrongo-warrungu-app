package com.example.warrungu.room

import androidx.room.Entity
import androidx.room.PrimaryKey

val DICT_ID_EXTRA = "dictExtra"

@Entity()
data class DictionaryItem(
    @PrimaryKey val id: Int,
    val english: String?,
    val language: String?,
    val pronunciation: String?,
    val description: String?,
    val category: String?,
    val type: String?,
    val image: String?,
//    val audio: String

)