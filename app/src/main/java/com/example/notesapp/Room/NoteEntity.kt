package com.example.notesapp.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity()
data class NoteEntity(


    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("content")
    val content: String,

    @PrimaryKey
        (autoGenerate = true,)
    var id : Long = 0

    )


    {




    }


