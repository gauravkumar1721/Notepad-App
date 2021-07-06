package com.example.notepadapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.notepadapp.data.Notepad
import com.example.notepadapp.databinding.FragmentNotesDetailsBinding
import com.example.notepadapp.databinding.FragmentNotesTopicBinding
import com.example.notepadapp.databinding.ViewsLayoutBinding

class NotesViewHolder(val bindingTopic: ViewsLayoutBinding): RecyclerView.ViewHolder(bindingTopic.root) {

    interface notesviewholdercommunicator{
        fun notesviewholderData(
            id: Int,
            heading: String,
            details: String
        )
    }
    private lateinit var obj: notesviewholdercommunicator
    fun values(data: notesviewholdercommunicator){
        obj = data
    }
    fun bind(notes: Notepad){
        bindingTopic.txt1.text = notes.heading
        bindingTopic.txt1.setOnClickListener {
            notes.id?.let { it1 ->
                obj.notesviewholderData(
                    it1,
                    notes.heading,
                    notes.details
                )
            }
        }
    }
}