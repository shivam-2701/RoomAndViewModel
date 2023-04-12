package com.example.roomandviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val ADD_NOTE_REQUEST=1
class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNoteFB:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelProvider= ViewModelProvider(this)
        recyclerView= findViewById(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        addNoteFB= findViewById(R.id.button_add_note)

        addNoteFB.setOnClickListener {
            val intent= Intent(this@MainActivity,AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }


        val adapter= NoteAdapter()
        recyclerView.adapter=adapter
        noteViewModel= viewModelProvider[NoteViewModel::class.java]
        noteViewModel.getAllNotes().observe(this, object:Observer<List<Note>>{
            override fun onChanged(value: List<Note>) {
                adapter.setNotes(value)
            }

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}