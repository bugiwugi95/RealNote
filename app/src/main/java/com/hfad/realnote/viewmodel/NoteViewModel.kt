package com.hfad.realnote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.hfad.realnote.model.Note
import com.hfad.realnote.model.NoteDao
import kotlinx.coroutines.launch

class NoteViewModel(private val dao: NoteDao) : ViewModel() {
    var newNotes = ""
    val notes = dao.getAll()

    fun addNote() {
        viewModelScope.launch {
            val note = Note()
            note.noteName = newNotes
            dao.insert(note)
        }


    }


}