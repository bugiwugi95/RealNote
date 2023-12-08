package com.hfad.realnote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.realnote.model.NoteDao
import kotlinx.coroutines.launch

class OpenNoteViewModel(noteId: Long, val dao: NoteDao) : ViewModel() {
    val note = dao.get(noteId)

    private val _navToList = MediatorLiveData<Boolean>(false)
    val navToList: LiveData<Boolean>
        get() = _navToList

    fun update() {
        viewModelScope.launch {
            note.value?.let { dao.update(it) }
            _navToList.value = true
        }

    }

    fun delete() {
        viewModelScope.launch {
            note.value?.let { dao.delete(it) }
            _navToList.value = true
        }

    }
}