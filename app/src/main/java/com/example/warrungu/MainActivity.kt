package com.example.warrungu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.warrungu.components.dictionary.Dictionary
import com.example.warrungu.components.games.WordGame
import com.example.warrungu.components.games.WordImageMatch

//import com.example.warrungu.room.DictionaryDatabase


class MainActivity : AppCompatActivity() {
//    private lateinit var dictionaryDatabase: DictionaryDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_cell)



        val accessDictionaryButton = findViewById<Button>(R.id.accessDictionaryButton)
        accessDictionaryButton.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    Dictionary::class.java
                )
            )
        }

        val accessDictionaryByTypeButton = findViewById<Button>(R.id.accessDictionaryByTypeButton)
        accessDictionaryByTypeButton.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    Catagories::class.java
                )
            )
        }

        val accessGamesLButton = findViewById<Button>(R.id.accessGamesButton)
        accessGamesLButton.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    GamesMenu::class.java
                )
            )
        }
    }
}