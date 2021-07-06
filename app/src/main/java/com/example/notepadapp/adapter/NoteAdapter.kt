package com.example.notepadapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notepadapp.R
import com.example.notepadapp.data.Notepad
import com.example.notepadapp.databinding.FragmentNotesDetailsBinding
import com.example.notepadapp.databinding.FragmentNotesTopicBinding
import com.example.notepadapp.databinding.ViewsLayoutBinding
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.currentCoroutineContext

class NoteAdapter: RecyclerView.Adapter<NotesViewHolder>(), NotesViewHolder.notesviewholdercommunicator {
    var notesdata = emptyList<Notepad>()
    interface NotesAdapterInterface{
        fun notesviewholdData(id: Int,heading: String, details: String)
    }
    lateinit var adaptObj: NotesAdapterInterface
    fun setDataAdapt(data: NotesAdapterInterface){
        adaptObj = data
    }
    override fun notesviewholderData(id: Int, heading: String, details: String) {
       adaptObj.notesviewholdData(id, heading, details)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ViewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notes = notesdata[position]
        holder.values(this)
        holder.bind(notes)

    }

    override fun getItemCount(): Int {
        return notesdata.size
    }
    fun setData(notes: List<Notepad>){
        this.notesdata = notes
        notifyDataSetChanged()

    }


}