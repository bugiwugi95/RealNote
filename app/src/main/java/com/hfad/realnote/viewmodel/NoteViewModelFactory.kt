package com.hfad.realnote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hfad.realnote.model.Note
import com.hfad.realnote.model.NoteDao
import kotlinx.coroutines.launch

class NoteViewModelFactory(private val dao: NoteDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}