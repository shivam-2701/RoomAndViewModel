package com.example.roomandviewmodel

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note:Note);

    @Update
    suspend fun update(note:Note);

    @Delete
    suspend fun delete(note:Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes():LiveData<List<Note>>

}