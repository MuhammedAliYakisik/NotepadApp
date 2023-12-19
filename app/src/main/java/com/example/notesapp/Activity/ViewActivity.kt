package com.example.notesapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        binding = ActivityViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val getintent = intent
        val title = getintent.getStringExtra("Title")
        val content = getintent.getStringExtra("Content")



        binding.title.text = title
        binding.content.text = content




    }








}