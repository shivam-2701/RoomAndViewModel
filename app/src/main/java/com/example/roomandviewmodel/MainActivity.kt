package com.example.roomandviewmodel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNoteFb: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelProvider= ViewModelProvider(this)
        recyclerView= findViewById(R.id.recycler_view)
        addNoteFb= findViewById(R.id.button_add_note)
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

        addNoteFb.setOnClickListener {
            val intent = Intent(baseContext,AddNoteActivity::class.java)
            getResult.launch(intent)
        }

    }
//    functional expression for getting the result from another activity
    private val getResult= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== Activity.RESULT_OK){
            val title = it.data!!.getStringExtra(Constants.EXTRA_TITLE)
            val desc= it.data!!.getStringExtra(Constants.EXTRA_DESCRIPTION)
            val pri= it.data!!.getIntExtra(Constants.EXTRA_PRIORITY,1)

            val note = Note(title!!,desc!!,pri)
            noteViewModel.insert(note)
            Toast.makeText(baseContext,"Note Saved",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(baseContext,"Note not saved",Toast.LENGTH_SHORT).show()
        }
    }
}