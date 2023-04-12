package com.example.roomandviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(): RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private  var notes:List<Note> = ArrayList<Note>()
    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var textViewTitle:TextView
        lateinit var textViewDescription:TextView
        lateinit var textViewPriority:TextView

        init{
            textViewTitle= itemView.findViewById(R.id.text_view_title)
            textViewDescription = itemView.findViewById(R.id.text_view_description)
            textViewPriority = itemView.findViewById(R.id.text_view_priority)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
//        TODO("Not yet implemented")
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item,parent,false)

        return NoteHolder(itemView)

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
//        TODO("Not yet implemented")

        val currNote = notes.get(position)
        holder.textViewTitle.text= currNote.title
        holder.textViewDescription.text= currNote.description
        holder.textViewPriority.text= currNote.priority.toString()
    }

    fun setNotes(notes:List<Note>){
        this.notes= notes
        notifyDataSetChanged()
    }
    fun getNotesAt(position: Int):Note{
        return notes.get(position)
    }
}