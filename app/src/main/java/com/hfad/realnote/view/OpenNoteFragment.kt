package com.hfad.realnote.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.realnote.R
import com.hfad.realnote.databinding.FragmentNoteBinding
import com.hfad.realnote.databinding.FragmentOpenNoteBinding
import com.hfad.realnote.model.NoteDatabase
import com.hfad.realnote.viewmodel.NoteViewModelFactory
import com.hfad.realnote.viewmodel.OpenNoteViewModel
import com.hfad.realnote.viewmodel.OpenNoteViewModelFactory

class OpenNoteFragment : Fragment() {

    private var _binding: FragmentOpenNoteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOpenNoteBinding.inflate(inflater, container, false)
        val args = OpenNoteFragmentArgs.fromBundle(requireArguments()).noteId
        val application = requireNotNull(this.activity).application
        val dao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = OpenNoteViewModelFactory(args, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)[OpenNoteViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.navToList.observe(viewLifecycleOwner){navigate ->
            if (navigate){
                view?.findNavController()
                    ?.navigate(R.id.action_openNoteFragment_to_noteFragment)
            }

        }
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}