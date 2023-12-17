package com.example.notesapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.R
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.notesapp.Adapter.Adapter
import com.example.notesapp.Room.NoteDao
import com.example.notesapp.Room.NoteDatabase
import com.example.notesapp.Room.NoteEntity
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.RecyclerRowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteList: ArrayList<NoteEntity>
    private lateinit var noteAdapter : Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        noteList = ArrayList<NoteEntity>()
        noteAdapter = Adapter(noteList)




        Thread{
            val db = Room.databaseBuilder(applicationContext,NoteDatabase::class.java,"Notes").build()
            val daom = db.noteDao()
            noteList = daom.getall() as ArrayList<NoteEntity>
            runOnUiThread {
                binding.recyclerview.layoutManager = LinearLayoutManager(this)
                noteAdapter = Adapter(noteList)
                binding.recyclerview.adapter = noteAdapter



            }

            noteAdapter.notifyDataSetChanged()

        }.start()

       





    }
    fun add(v:View){
        val intent = Intent(applicationContext, SaveActivity::class.java)
        startActivity(intent)
    }



}