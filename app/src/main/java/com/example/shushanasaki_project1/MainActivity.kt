package com.example.shushanasaki_project1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var scoreCount: Int = 0
    private var strikeCount: Int = 0
    private lateinit var startButton: Button
    private lateinit var firstBox: TextView
    private lateinit var secondBox: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.start_button)
        firstBox = findViewById(R.id.first_box)
        secondBox = findViewById(R.id.second_box)

    }

    private fun randNum() {
        firstBox.text = (Random.nextInt(100) + 1).toString()
        while (secondBox.text.toString() != firstBox.text.toString()) {
            secondBox.text = (Random.nextInt(100) + 1).toString()
        }
    }

    private fun checkLargerNum(x: Int, y: Int): Int {
        if (x > y) {
            return x
        }
        return y
    }
}