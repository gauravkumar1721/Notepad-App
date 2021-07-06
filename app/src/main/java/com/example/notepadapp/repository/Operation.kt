package com.example.notepadapp.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notepadapp.data.Notepad

@Dao
interface Operation {

    @Insert
    suspend fun insertNotes(notepad: Notepad)

    @Update
    suspend fun updateNotes(notepad: Notepad)

    @Delete
    suspend fun deleteNotes(notepad: Notepad)

    @Query("Select * From notepad")
    fun getallnotes(): LiveData<List<Notepad>>
}