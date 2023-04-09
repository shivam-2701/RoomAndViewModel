package com.example.roomandviewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository(application: Application) {

    private lateinit var noteDao:NoteDao
    private lateinit var allNotes: LiveData<List<Note>>

    init {
        val dataBase = NoteDataBase.getInstance(application);
        noteDao = dataBase.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note:Note){

        CoroutineScope(Dispatchers.IO).launch{
            noteDao.insert(note)
        }

    }
    fun update(note:Note){
        CoroutineScope(Dispatchers.IO).launch{
            noteDao.update(note)
        }
    }
    fun delete(note:Note){
        CoroutineScope(Dispatchers.IO).launch{
            noteDao.delete(note)
        }
    }
    fun deleteAllNotes(){
        CoroutineScope(Dispatchers.IO).launch{
            noteDao.deleteAllNotes()
        }
    }
    fun getAllNotes():LiveData<List<Note>>{
      return allNotes;
    }

}