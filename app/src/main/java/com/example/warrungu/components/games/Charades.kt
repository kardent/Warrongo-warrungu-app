package com.example.warrungu.components.games

import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.widget.Toast
import java.util.*
import kotlin.math.sqrt
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.hardware.Sensor
import android.hardware.SensorManager
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
import java.util.Objects
import kotlin.random.Random

class Charades : AppCompatActivity() {
    private lateinit var dictionaryDatabase: DictionaryDatabase
    private lateinit var rows: List<DictionaryItem>
    private var sensor: Sensor? = null
    var pos = 0

    private var sensorManager: SensorManager? = null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charades)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Objects.requireNonNull(sensorManager)!!.registerListener(sensorListener, sensorManager!!
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH

        initGame()
//        findViewById<Button>(R.id.tickButton).setOnClickListener(this)
//        findViewById<Button>(R.id.crossButton).setOnClickListener(this)
    }

    fun initGame(){
        dictionaryDatabase = DictionaryDatabase.getInstance(applicationContext)
        rows = dictionaryDatabase.dao.getGameCharades()

        Log.d("XXX",rows[0].language.toString())

////        SET TITLE
        val wordTextView = findViewById<TextView>(R.id.wordTextView)
        wordTextView.text = rows[pos].language
        val transTextView = findViewById<TextView>(R.id.translationTextView)
        transTextView.text = rows[pos].english
//
        var img = findViewById<ImageView>(R.id.wordImageView)
        img.setImageDrawable(makeDrawable("photos/" + rows[pos].image))

        Handler().postDelayed({
            onResume()
        },3000)
    }

    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration
            currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta
            if (acceleration > 5) {
                Toast.makeText(applicationContext, "Shake event detected", Toast.LENGTH_SHORT).show()
                onPause()
                initGame()
            }
        }
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }
    override fun onResume() {
        sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(
            Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )
        super.onResume()
    }
    override fun onPause() {
        sensorManager!!.unregisterListener(sensorListener)
        super.onPause()
    }

    private fun makeDrawable(path: String): BitmapDrawable {
        val inputStream = assets.open(path)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val drawable = BitmapDrawable(resources, bitmap)
        return drawable
    }
}