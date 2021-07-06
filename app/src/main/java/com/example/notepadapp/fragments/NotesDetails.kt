package com.example.notepadapp.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notepadapp.R
import com.example.notepadapp.adapter.NoteAdapter
import com.example.notepadapp.data.Notepad
import com.example.notepadapp.databinding.FragmentNotesDetailsBinding
import com.example.notepadapp.databinding.FragmentNotesTopicBinding
import com.example.notepadapp.repository.NotepadDB
import com.example.notepadapp.viewmodel.NotesViewModel


class NotesDetails : Fragment() {
    var NavCont: NavController? = null
    lateinit var binding: FragmentNotesDetailsBinding
    lateinit var database: NotepadDB
    private lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notes_details, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavCont = Navigation.findNavController(view)

        val dataheading = arguments?.getString("my notes heading")
        val datadetails = arguments?.getString("my notes details")
        setListener(dataheading, datadetails)
        deletedata(dataheading, datadetails)
        if (datadetails != null && dataheading != null) {
            val notestopic = binding.notestitle.text.toString()
            val notesdetails = binding.notesDetails.text.toString()
            val notes = Notepad(heading = notestopic, details = notesdetails)
            Log.d("my heading", dataheading)
            Log.d("my details", datadetails)
            Log.d("Success", "data pass i'm good")
            binding.notestitle.setText(dataheading)
            binding.notesDetails.setText(datadetails)
            //NavCont?.popBackStack()
            //NavCont?.popBackStack(R.id.notesDetails, true)
            //NavCont?.popBackStack()

        } else {
            Log.d("wrong data", "wrong wrong")
        }
    }

    private fun setListener(dataheading: String?, datadetails: String?) {
        binding.savebtn.setOnClickListener {
            if (datadetails != null && dataheading != null) {
                updateNotesDB()
            } else {
                Log.d("my text data", "mm")
                insertNotesToDB()
            }
        }
        binding.deletebtn.setOnClickListener {
            if (datadetails != null && dataheading != null) {
                deleteNotesDB()
            }
        }
    }
    private fun deletedata(dataheading: String?, datadetails: String?){
        if(dataheading!=null && datadetails!=null) {
            binding.deletebtn.visibility = View.VISIBLE
        }
    }

    private fun updateNotesDB() {
        val notesID = arguments?.getInt("my id")
        val notestopicfi = binding.notestitle.text.toString()
        val notesdetailsfi = binding.notesDetails.text.toString()
        val notesp = Notepad(id = notesID, heading = notestopicfi, details = notesdetailsfi)
        notesViewModel.updatemyNotes(notesp)
        Log.d("my dets", notestopicfi)
        Log.d("my dets", notesdetailsfi)
        Log.d("my updated data", "new updated data")
        Toast.makeText(requireContext(), "Succesfully inserted", Toast.LENGTH_LONG).show()
        NavCont?.popBackStack()
    }

    private fun insertNotesToDB() {
        val notestopic = binding.notestitle.text.toString()
        val notesdetails = binding.notesDetails.text.toString()

        if (inputCheck(notestopic, notesdetails)) {
            val notes = Notepad(heading = notestopic, details = notesdetails)

            notesViewModel.addNotes(notes)
            Log.d("Success", "data pass")
            Toast.makeText(requireContext(), "Succesfully inserted", Toast.LENGTH_LONG).show()

            //NavCont?.popBackStack(R.id.notesDetails, true)
            NavCont?.popBackStack()
            // back through navc
        } else {
            Toast.makeText(requireContext(), "Pls insert all the fields", Toast.LENGTH_LONG).show()
        }

    }
    private fun deleteNotesDB(){
        val notesID = arguments?.getInt("my id")
        val notestopicfi = binding.notestitle.text.toString()
        val notesdetailsfi = binding.notesDetails.text.toString()
        val notesp = Notepad(id = notesID, heading = notestopicfi, details = notesdetailsfi)
        notesViewModel.DeleteNotes(notesp)
        NavCont?.popBackStack()
    }
    private fun inputCheck(notestopic: String, notesdetails: String): Boolean {
        return !(TextUtils.isEmpty(notestopic) && TextUtils.isEmpty(notesdetails))
    }



}