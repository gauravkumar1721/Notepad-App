package com.example.notepadapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notepad")
data class Notepad(
    @PrimaryKey(autoGenerate = true) // it will increment id as we push data
    val id: Int? = null,
    val heading: String,
    val details: String
)
