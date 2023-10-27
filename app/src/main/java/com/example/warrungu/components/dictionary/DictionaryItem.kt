package com.example.warrungu.components.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.warrungu.R
import com.example.warrungu.room.DictionaryDatabase

class DictionaryItem : AppCompatActivity() {
    private lateinit var dictionaryDatabase: DictionaryDatabase
    companion object {
        const val ID = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_item)

        val id = intent.getStringExtra(ID)
        dictionaryDatabase = DictionaryDatabase.getInstance(applicationContext)
        val item = dictionaryDatabase.dao.getDictionaryItemById(id.toString())

        val title = findViewById<TextView>(R.id.title)
        title.text = item[0].language
        val english = findViewById<TextView>(R.id.english)
        english.text = item[0].english
        val type = findViewById<TextView>(R.id.type)
        type.text = item[0].type
        val cat = findViewById<TextView>(R.id.cat)
        cat.text = item[0].category

    }
}