package com.example.airena

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    private val colors = arrayOf(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN)
    private var currentColorIndex = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val circleView = findViewById<View>(R.id.circle_view)

        val changeColorRunnable = object : Runnable {
            override fun run() {
                circleView.setBackgroundColor(colors[currentColorIndex])
                currentColorIndex = (currentColorIndex + 1) % colors.size
                handler.postDelayed(this, 2000)
            }
        }
        handler.post(changeColorRunnable)
    }
}
