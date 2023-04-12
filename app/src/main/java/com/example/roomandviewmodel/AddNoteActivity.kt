package com.example.roomandviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast


class AddNoteActivity : AppCompatActivity() {
    private lateinit var editTextTitle:EditText
    private lateinit var editTextDescription:EditText
    private lateinit var numberPickerPriority: NumberPicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextTitle= findViewById(R.id.edit_text_title)
        editTextDescription= findViewById(R.id.edit_text_description)
        numberPickerPriority= findViewById(R.id.number_picker_priority)

        numberPickerPriority.minValue=1
        numberPickerPriority.maxValue=10

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title= "Add Note"

    }

    private fun saveNote(){
        val title = editTextTitle.text.toString()
        val description= editTextDescription.text.toString()
        val priorityEntry = numberPickerPriority.value

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this@AddNoteActivity,"Please insert a title and description",Toast.LENGTH_SHORT).show()
            return
        }
//    Create a  another viewModel variable to do dataBase operation

//        OR

//    Use startActivityForResult method to make this activity act as a form
        val data= Intent()
        data.putExtra(Constants.EXTRA_TITLE,title)
        data.putExtra(Constants.EXTRA_DESCRIPTION,description)
        data.putExtra(Constants.EXTRA_PRIORITY,priorityEntry)

//        this method set the state of data sent back
        setResult(RESULT_OK,data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.add_note_menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save_note ->{
                saveNote()
                return true
            }
            else-> return super.onOptionsItemSelected(item)
        }

    }
}