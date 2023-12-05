package com.hfad.realnote.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.realnote.databinding.NoteItemBinding
import com.hfad.realnote.model.Note


class NoteItemAdapter : ListAdapter<Note, NoteItemAdapter.NoteItemVH>(NoteItemDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemVH {
        return NoteItemVH.inflateFrom(parent)
    }


    override fun onBindViewHolder(holder: NoteItemVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class NoteItemVH(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): NoteItemVH {
                val layoutInflate = LayoutInflater.from(parent.context)
                val binding = NoteItemBinding.inflate(layoutInflate, parent, false)
                return NoteItemVH(binding)

            }
        }

        fun bind(item: Note) {
            binding.note = item
        }
    }


}