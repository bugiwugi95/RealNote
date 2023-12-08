package com.hfad.realnote.view.adapter


import androidx.recyclerview.widget.DiffUtil
import com.hfad.realnote.model.Note

class NoteItemDiffUtil : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
        (oldItem.noteId == newItem.noteId)

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = (oldItem == newItem)
}