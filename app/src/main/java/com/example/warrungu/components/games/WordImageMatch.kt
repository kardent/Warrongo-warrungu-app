package com.example.warrungu.components.games

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.warrungu.R
import com.example.warrungu.room.DictionaryDatabase
import com.example.warrungu.room.DictionaryItem
import kotlin.random.Random

class WordImageMatch : AppCompatActivity(), View.OnClickListener   {
    private lateinit var dictionaryDatabase: DictionaryDatabase
    private lateinit var answers: Array<Boolean>
    private lateinit var rows: List<DictionaryItem>
    var answery: Int = 0
//    var score: Int = 0
    companion object {
        const val SCORE: String = "0"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_image_match)

        initGame()
        findViewById<Button>(R.id.tickButton).setOnClickListener(this)
        findViewById<Button>(R.id.crossButton).setOnClickListener(this)
    }

    fun initGame(){

        dictionaryDatabase = DictionaryDatabase.getInstance(applicationContext)
        rows = dictionaryDatabase.dao.getGameItems()
        val score = intent.getStringExtra(SCORE)

        findViewById<TextView>(R.id.scoreTextView).text = "Score:" + score.toString()

////        SET TITLE
        val wordTextView = findViewById<TextView>(R.id.wordTextView)
        answery = Random.nextInt(0,2)
        wordTextView.text = rows[answery].language

        var img = findViewById<ImageView>(R.id.imageView)
        img.setImageDrawable(makeDrawable("photos/" + rows[0].image))

    }

    override fun onClick(p0: View?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)

        var score = intent.getStringExtra(SCORE)?.toInt()
        var new_score = 0

        val ans_id = getResources().getResourceName(p0!!.getId())

//        Check answer
        if ((ans_id == "com.example.warrungu:id/tickButton" && answery == 0) || (ans_id == "com.example.warrungu:id/crossButton" && answery == 1) ){
            builder.setTitle("CORRECT")
                //do your work here
//            score++
        } else{
            builder.setTitle("INCORRECT")
            builder.setMessage("ANSWER: " + rows[0].language + " which means " + rows[0].english)
        }

        builder.show()
        Handler().postDelayed({

            var game = Random.nextBoolean()

            if(game){
                val intent = Intent(this, WordGame::class.java).putExtra(WordGame.SCORE,score)
                p0.context.startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, WordImageMatch::class.java).putExtra(WordImageMatch.SCORE,score)
                p0.context.startActivity(intent)
                finish()
            }
        }, 3000)
    }

    fun makeDrawable(path: String): BitmapDrawable {
        val inputStream = assets.open(path)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val drawable = BitmapDrawable(resources, bitmap)
        return drawable
    }
}