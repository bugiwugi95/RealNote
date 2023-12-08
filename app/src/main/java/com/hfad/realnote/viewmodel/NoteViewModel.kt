package com.hfad.realnote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.realnote.model.Note
import com.hfad.realnote.model.NoteDao
import kotlinx.coroutines.launch

class NoteViewModel(private val dao: NoteDao) : ViewModel() {
    var newNotes = ""
    val notes = dao.getAll()

    private val _navToNote = MutableLiveData<Long?>()
    val navToNote: LiveData<Long?>
        get() = _navToNote


    fun onNoteClicked(noteId: Long) {
        _navToNote.value = noteId

    }

    fun onNoteNavigated() {
        _navToNote.value = null

    }

    fun addNote() {
        viewModelScope.launch {
            val note = Note()
            note.noteName = newNotes
            dao.insert(note)
        }


    }




}