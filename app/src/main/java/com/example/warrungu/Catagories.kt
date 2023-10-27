package com.example.warrungu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.warrungu.components.dictionary.DictionaryByType

class Catagories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catagories)

        val accessNounsButton = findViewById<Button>(R.id.nounsButton)
        accessNounsButton.setOnClickListener {
            startActivity(
                Intent(
                    this@Catagories,
                    DictionaryByType::class.java
                ).putExtra(DictionaryByType.WORD_TYPE,"Noun")
            )
        }

        val accessVerbButton = findViewById<Button>(R.id.verbsButton)
        accessVerbButton.setOnClickListener {
            startActivity(
                Intent(
                    this@Catagories,
                    DictionaryByType::class.java
                ).putExtra(DictionaryByType.WORD_TYPE,"adverb")
            )
        }

        val accessAdjbButton = findViewById<Button>(R.id.adjectivesButton)
        accessAdjbButton.setOnClickListener {
            startActivity(
                Intent(
                    this@Catagories,
                    DictionaryByType::class.java
                ).putExtra(DictionaryByType.WORD_TYPE,"adjective")
            )
        }

        val accessPronounButton = findViewById<Button>(R.id.pronounsButton)
        accessPronounButton.setOnClickListener {
            startActivity(
                Intent(
                    this@Catagories,
                    DictionaryByType::class.java
                ).putExtra(DictionaryByType.WORD_TYPE,"pronoun")
            )
        }
    }
}