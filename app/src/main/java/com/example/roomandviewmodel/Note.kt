package com.example.roomandviewmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

//The table name is class name by default but argument tableName changes that
@Entity(tableName = "note_table")
data class Note (val title:String, var description:String,var priority: Int){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
    override fun toString(): String {
        return "Note(title='$title', description='$description', priority=$priority, id=$id)"
    }
}