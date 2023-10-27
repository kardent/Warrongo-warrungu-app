package com.example.warrungu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.warrungu.components.games.Charades
import com.example.warrungu.components.games.WordGame

class GamesMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_menu)

        val accessWordGame = findViewById<Button>(R.id.accessWordGame)
        accessWordGame.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    WordGame::class.java
                )
            )
        }

        val accessCharades = findViewById<Button>(R.id.accessCharades)
        accessCharades.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Charades::class.java
                )
            )
        }
    }
}