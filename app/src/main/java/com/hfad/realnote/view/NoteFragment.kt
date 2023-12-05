package com.hfad.realnote.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hfad.realnote.databinding.FragmentNoteBinding
import com.hfad.realnote.model.NoteDatabase
import com.hfad.realnote.viewmodel.NoteViewModel
import com.hfad.realnote.viewmodel.NoteViewModelFactory


class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = NoteViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory
        )[NoteViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = NoteItemAdapter()
        binding.recyclerviewNote.adapter = adapter
        viewModel.notes.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}