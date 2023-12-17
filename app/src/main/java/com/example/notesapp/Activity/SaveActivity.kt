package com.example.notesapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.notesapp.Adapter.Adapter
import com.example.notesapp.Room.NoteDao
import com.example.notesapp.Room.NoteDatabase
import com.example.notesapp.Room.NoteEntity
import com.example.notesapp.databinding.ActivitySaveBinding

class SaveActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveBinding
    private lateinit var db: NoteDatabase
    private lateinit var dao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = Room.databaseBuilder(applicationContext, NoteDatabase::class.java, "Notes")
            .allowMainThreadQueries()


            .build()


        dao = db.noteDao()

    }

    fun save(v: View) {
        if (binding.TitleText.text != null && binding.ContentText.text != null) {
            var save = NoteEntity(
                binding.TitleText.text.toString(),
                binding.ContentText.text.toString()


            )
            dao.adduser(save)




            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }



    }
}