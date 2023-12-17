package com.example.notesapp.Adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.room.Database
import androidx.room.Room
import com.example.notesapp.Activity.SaveActivity
import com.example.notesapp.Room.NoteDao
import com.example.notesapp.Room.NoteDatabase
import com.example.notesapp.Room.NoteEntity
import com.example.notesapp.databinding.RecyclerRowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Adapter(var noteList : ArrayList<NoteEntity>) : RecyclerView.Adapter<Adapter.AdapterHolder>() {
    class AdapterHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.binding.rowtext.text = noteList.get(position).title
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SaveActivity::class.java)
            holder.itemView.context.startActivity(intent)


        }
        holder.binding.deletebutton.setOnClickListener {
            val note = noteList[position].id ?:return@setOnClickListener
            if (note != null) {
                GlobalScope.launch(Dispatchers.IO) {
                    val db = Room.databaseBuilder(
                        holder.itemView.context.applicationContext,
                        NoteDatabase::class.java,
                        "Notes"
                    ).build()
                    val dao = db.noteDao()
                    dao.deletebyId(note)
                }
                noteList.removeAt(position)
                notifyItemRemoved(position)


            }


        }



    }
}
