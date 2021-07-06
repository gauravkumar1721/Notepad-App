package com.example.notepadapp.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notepadapp.data.Notepad

@Database(entities = [Notepad::class], version = 2)
abstract class NotepadDB : RoomDatabase(){


    //linking of dao
    abstract fun NotepadDao(): Operation

    companion object{
        @Volatile
        private var Instance: NotepadDB? = null

        fun getNotes(context: Context): NotepadDB{
            val tempinstance = Instance
            if(tempinstance!=null){
                return tempinstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotepadDB::class.java,
                    "NotepadDB"
                ).fallbackToDestructiveMigration()
                    .build()
                Instance = instance
                return instance
            }
        }
    }
}