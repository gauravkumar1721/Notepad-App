package com.example.notepadapp.repository

import androidx.lifecycle.LiveData
import com.example.notepadapp.data.Notepad

class NotesRepo(private val operation: Operation) {

    val readAllData: LiveData<List<Notepad>> = operation.getallnotes()

    suspend fun addNotes(notepad: Notepad){
        operation.insertNotes(notepad)
    }
    suspend fun updatemyNotes(notepad: Notepad){
        operation.updateNotes(notepad)
    }
    suspend fun DeleteNotes(notepad: Notepad){
        operation.deleteNotes(notepad)
    }
}