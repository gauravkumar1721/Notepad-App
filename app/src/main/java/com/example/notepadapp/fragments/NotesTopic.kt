package com.example.notepadapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepadapp.R
import com.example.notepadapp.adapter.NoteAdapter
import com.example.notepadapp.data.Notepad
import com.example.notepadapp.databinding.FragmentNotesTopicBinding
import com.example.notepadapp.databinding.ViewsLayoutBinding
import com.example.notepadapp.repository.NotepadDB
import com.example.notepadapp.viewmodel.NotesViewModel

class NotesTopic : Fragment(), NoteAdapter.NotesAdapterInterface {
    var NavCont: NavController?=null
    lateinit var binding: FragmentNotesTopicBinding
    lateinit var database: NotepadDB
    private lateinit var mNotesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes_topic, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = NoteAdapter()
        binding.rcv.adapter = adapter
        adapter.setDataAdapt(this)
        binding.rcv.layoutManager = LinearLayoutManager(requireContext())

        //

        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        mNotesViewModel.readAllNotes.observe(viewLifecycleOwner, Observer { Notepad ->
          adapter.setData(Notepad)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //database = Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "contactDB").build()
        NavCont = Navigation.findNavController(view)
        binding.addFab.setOnClickListener {
            NavCont?.navigate(R.id.action_notesTopic_to_notesDetails)
        }
    }

    override fun notesviewholdData(id:Int, heading: String, details: String) {
        Log.d("my frag first", heading)
        Log.d("my frag first", details)
        val bundle = bundleOf(Pair("my id", id),Pair("my notes heading", heading), Pair("my notes details", details))
        NavCont?.navigate(R.id.action_notesTopic_to_notesDetails, bundle)
    }
}