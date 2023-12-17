package com.example.notesapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

    @Dao
    interface NoteDao {
        @Query("SELECT * FROM NoteEntity")
         fun getall(): List<NoteEntity>

        @Insert
         fun adduser(vararg note: NoteEntity)


        @Query("DELETE FROM NoteEntity WHERE id = :note")
        suspend fun deletebyId(note: Long)


        @Delete
        fun delete(note: NoteEntity)

        @Update
        fun update(note: NoteEntity)



    }
