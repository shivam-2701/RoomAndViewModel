package com.example.roomandviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelProvider= ViewModelProvider(this)
        recyclerView= findViewById(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter= NoteAdapter()
        recyclerView.adapter=adapter
        noteViewModel= viewModelProvider[NoteViewModel::class.java]
        noteViewModel.getAllNotes().observe(this, object:Observer<List<Note>>{
            override fun onChanged(value: List<Note>) {
                adapter.setNotes(value)
            }

        })
    }
}