package com.example.roomandviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application:Application):AndroidViewModel(application){

    private lateinit var repository: NoteRepository
    private lateinit var allNotes:LiveData<List<Note>>

    init {
        repository= NoteRepository(application)
        allNotes= repository.getAllNotes()
    }

    fun insert(note:Note){
        repository.insert(note)
    }
    fun update(note:Note){
        repository.update(note)
    }
    fun delete(note:Note){
        repository.delete(note)
    }
    fun deleteAllNotes(){
        repository.deleteAllNotes()
    }
    fun getAllNotes():LiveData<List<Note>>{
        return allNotes
    }

}