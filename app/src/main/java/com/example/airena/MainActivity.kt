package com.example.airena

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener(View.OnClickListener {
            // Affiche un Toast lorsque le bouton est cliqué
            Toast.makeText(this@MainActivity, "Let's go", Toast.LENGTH_SHORT).show()
        })
    }
}
