package com.example.notepadapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepadapp.data.Notepad
import com.example.notepadapp.repository.NotepadDB
import com.example.notepadapp.repository.NotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.koinApplication

// android viewmodel contain application reference
class NotesViewModel(application: Application): AndroidViewModel(application) {

    val readAllNotes: LiveData<List<Notepad>>
    private val repository: NotesRepo

    init{
        val notesDao = NotepadDB.getNotes(application).NotepadDao()
        repository = NotesRepo(notesDao)
        readAllNotes = repository.readAllData
    }
    fun addNotes(notepad: Notepad){
        //Dispatchers.io for running it in backend
        viewModelScope.launch(Dispatchers.IO) {
          repository.addNotes(notepad)
        }
    }
    fun updatemyNotes(notepad: Notepad){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatemyNotes(notepad)
        }
    }
    fun DeleteNotes(notepad: Notepad){
        viewModelScope.launch(Dispatchers.IO) {
            repository.DeleteNotes(notepad)
        }
    }

}