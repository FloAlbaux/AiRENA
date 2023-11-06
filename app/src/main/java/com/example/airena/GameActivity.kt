package com.example.airena

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private val colors = arrayOf(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.CYAN)
    private val blankColor = Color.WHITE
    private var currentColorIndex = -1
    private val handler = Handler(Looper.getMainLooper())
    private var lives = 3
    private var isCircleClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val circleView = findViewById<View>(R.id.circle_view)
        circleView.setBackgroundColor(blankColor)

        circleView.setOnClickListener {
            isCircleClicked = true
            circleView.setBackgroundColor(blankColor)
        }

        val newColorIndex = getRandomColorIndex()
        circleView.setBackgroundColor(colors[newColorIndex])
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (!isCircleClicked) {
                    handleMissedCircleClick()
                }
                circleView.setBackgroundColor(colors[getRandomColorIndex()])
                isCircleClicked = false
                handler.postDelayed(this, 2000)
            }
        }, 2000)
    }

    private fun handleMissedCircleClick() {
        lives--
        updateHeartsVisibility()
        if (lives == 0) {
            Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun updateHeartsVisibility() {
        val heart1 = findViewById<View>(R.id.heart1)
        val heart2 = findViewById<View>(R.id.heart2)
        val heart3 = findViewById<View>(R.id.heart3)

        heart1.visibility = if (lives >= 1) View.VISIBLE else View.INVISIBLE
        heart2.visibility = if (lives >= 2) View.VISIBLE else View.INVISIBLE
        heart3.visibility = if (lives >= 3) View.VISIBLE else View.INVISIBLE
    }

    private fun getRandomColorIndex(): Int {
        var newColorIndex: Int
        do {
            newColorIndex = Random.nextInt(colors.size)
        } while (newColorIndex == currentColorIndex)
        return newColorIndex
    }
}
