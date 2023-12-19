package com.example.notesapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.notesapp.R
import com.example.notesapp.Room.NoteDao
import com.example.notesapp.Room.NoteDatabase
import com.example.notesapp.Room.NoteEntity
import com.example.notesapp.databinding.ActivityUpdateBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NoteDatabase
    private lateinit var dao: NoteDao
    private lateinit var noteList: ArrayList<NoteEntity>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        noteList = ArrayList<NoteEntity>()


        val db = Room.databaseBuilder(applicationContext, NoteDatabase::class.java, "Notes")
            .allowMainThreadQueries()


            .build()


        dao = db.noteDao()

        val note = intent

        val title = note.getStringExtra("Title")
        val content = note.getStringExtra("Content")

        binding.TitleText.setText(title)
        binding.ContentText.setText(content)
    }
    fun update(v: View){

        val title = binding.TitleText.text.toString()
        val content = binding.ContentText.text.toString()

        if (title.isNotBlank() && content.isNotBlank()) {
            val noteId = intent.getLongExtra("NoteId", -1L)

            if (noteId != -1L) {
                GlobalScope.launch(Dispatchers.IO) {
                    dao.update(NoteEntity(title,content,noteId))
                }

                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }




    }
}