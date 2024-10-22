package com.example.task_011

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val notes: MutableList<User> = mutableListOf()

    private lateinit var toolbarMain: Toolbar

    private lateinit var listViewLV: ListView
    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
    private lateinit var saveBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Каталог пользователей"

        listViewLV = findViewById(R.id.listViewLV)
        nameET = findViewById(R.id.nameET)
        ageET = findViewById(R.id.ageET)
        saveBTN = findViewById(R.id.saveBTN)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notes)
        listViewLV.adapter = adapter

        saveBTN.setOnClickListener{
            if (nameET.text.toString() != "" || ageET.text.toString() != "")
            {
                notes.add(User(nameET.text.toString(), ageET.text.toString().toInt()))
                adapter.notifyDataSetChanged()
                nameET.text.clear()
                ageET.text.clear()
            }
        }

        listViewLV.onItemClickListener = AdapterView.OnItemClickListener {parent, v, position, id ->
            val note = adapter.getItem(position)
            adapter.remove(note)
            Toast.makeText(this, "Заметка удалена: $note", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain->{
                Toast.makeText(
                    applicationContext,
                    "Работа завершена",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}