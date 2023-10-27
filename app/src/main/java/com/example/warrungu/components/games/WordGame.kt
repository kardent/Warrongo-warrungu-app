package com.example.warrungu.components.games

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.warrungu.R
import com.example.warrungu.room.DictionaryDatabase
import com.example.warrungu.room.DictionaryItem
import kotlin.random.Random

class WordGame : AppCompatActivity(), View.OnClickListener  {
    private lateinit var dictionaryDatabase: DictionaryDatabase
    private lateinit var answers: Array<Boolean>
    private lateinit var rows: List<DictionaryItem>
    var correct: Int = 0

    companion object {
        const val SCORE: String = "0"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_game)
        correct = Random.nextInt(0,3)

        initGame(correct)

//        Add event listeners to images
        for(i in 1..4){
            Log.d("cwwfd",i.toString())
            findViewById<ImageView>(resources.getIdentifier("img_" + i.toString(), "id", packageName)).setOnClickListener(this)
        }
    }

    fun initGame(correct: Int){

        dictionaryDatabase = DictionaryDatabase.getInstance(applicationContext)
        rows = dictionaryDatabase.dao.getGameItems()

//        SET TITLE
        val title = findViewById<TextView>(R.id.word)
        title.text = rows[correct].language

//        SET IMAGES
        rows.forEachIndexed{ index, row ->
            var img = findViewById<ImageView>(resources.getIdentifier("img_" + (index + 1).toString(), "id", packageName))
            img.setImageDrawable(makeDrawable("photos/" + row.image))
        }

    }

    override fun onClick(p0: View?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)

        val ans_id = getResources().getResourceName(p0!!.getId())
        val ans = ans_id[ans_id.length - 1]
        var score = 0

//        Check answer
        if (ans.toString() == (correct + 1).toString()){
            builder.setTitle("CORRECT")
        } else{
            builder.setTitle("INCORRECT")
            builder.setMessage("ANSWER WAS: " + rows[correct].english)
        }
        builder.show()

        Handler().postDelayed({

            var game = Random.nextBoolean()

            if(game){
                val intent = Intent(this, WordGame::class.java).putExtra(SCORE,score)
                p0.context.startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, WordImageMatch::class.java).putExtra(WordImageMatch.SCORE,score)
                p0.context.startActivity(intent)
                finish()
            }
        }, 1500)
    }

    fun makeDrawable(path: String): BitmapDrawable {
        val inputStream = assets.open(path)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val drawable = BitmapDrawable(resources, bitmap)
        return drawable
    }
}