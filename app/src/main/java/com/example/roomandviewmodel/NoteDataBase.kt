package com.example.roomandviewmodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Database(entities = [Note::class] , version=1)
abstract class NoteDataBase:RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{

        private var instance:NoteDataBase? = null

        @Synchronized
        fun getInstance(context: Context):NoteDataBase{
            if(instance==null){
                instance= Room.databaseBuilder(context.applicationContext,
                NoteDataBase::class.java,"note_database")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
        private var roomCallback:RoomDatabase.Callback =object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDb(instance!!)
            }
        }

        private fun populateDb(db:NoteDataBase){
            val noteDao= db.noteDao()
            CoroutineScope(IO).launch {
                noteDao.insert(Note("Title 1","Description 1",1))
                noteDao.insert(Note("Title 2","Description 2",2))
                noteDao.insert(Note("Title 3","Description 3",3))
            }
        }


    }


}