package com.hfad.realnote.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hfad.realnote.databinding.FragmentNoteBinding
import com.hfad.realnote.model.NoteDatabase
import com.hfad.realnote.view.adapter.NoteItemAdapter
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
        val adapter = NoteItemAdapter {noteId->
            viewModel.onNoteClicked(noteId)
            //Snackbar.make(binding.root, "$noteId", Snackbar.LENGTH_LONG).show()
        }
        viewModel.navToNote.observe(viewLifecycleOwner) { noteId ->
            noteId?.let {
                val action = NoteFragmentDirections.actionNoteFragmentToOpenNoteFragment(it)
                this.findNavController().navigate(action)
                viewModel.onNoteNavigated()
            }

        }
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