package com.example.notesapp.Adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.notesapp.Room.NoteDatabase
import com.example.notesapp.Room.NoteEntity
import com.example.notesapp.Activity.ViewActivity
import com.example.notesapp.Activity.UpdateActivity
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
            val intent = Intent(holder.itemView.context, ViewActivity::class.java)
            intent.putExtra("Title", noteList.get(position).title)
            intent.putExtra("Content", noteList.get(position).content)
            holder.itemView.context.startActivity(intent)


        }
        holder.binding.deletebutton.setOnClickListener {
            val note = noteList[position].id ?: return@setOnClickListener
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
        holder.binding.updatebutton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java)
            intent.putExtra("NoteId", noteList[position].id)
            intent.putExtra("Title", noteList[position].title)
            intent.putExtra("Content", noteList[position].content)
            holder.itemView.context.startActivity(intent)




        }

    }
}
