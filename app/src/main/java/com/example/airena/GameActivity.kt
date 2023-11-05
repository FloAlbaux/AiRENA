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
    private var currentColorIndex = -1 // Initialise à -1 pour s'assurer que la première couleur est différente.
    private val handler = Handler(Looper.getMainLooper())
    private var lives = 3
    private var isCircleVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val circleView = findViewById<View>(R.id.circle_view)

        circleView.setOnClickListener {
            if (isCircleVisible) {
                // User clicked on the circle in time
                isCircleVisible = false
                // Choisir une couleur aléatoire différente de la couleur actuelle
                val newColorIndex = getRandomColorIndex()
                currentColorIndex = newColorIndex
                circleView.setBackgroundColor(colors[newColorIndex])
            }
        }

        // Update the circle color and handle missed clicks every 2 seconds
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isCircleVisible) {
                    // User missed clicking on the circle in time, reduce a life
                    handleMissedCircleClick()
                }
                // Choisir une couleur aléatoire différente de la couleur actuelle
                val newColorIndex = getRandomColorIndex()
                currentColorIndex = newColorIndex
                circleView.setBackgroundColor(colors[newColorIndex])
                isCircleVisible = true
                handler.postDelayed(this, 2000)
            }
        }, 2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }

    private fun handleMissedCircleClick() {
        if (isCircleVisible) {
            // User missed clicking on the circle in time, reduce a life
            lives--
            updateHeartsVisibility()
            if (lives == 0) {
                // Game over logic here
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        isCircleVisible = false
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
