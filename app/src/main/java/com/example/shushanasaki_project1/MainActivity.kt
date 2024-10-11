package com.example.shushanasaki_project1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
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
    var firstNum = 0;
    var secondNum = 0;
    private lateinit var mainView: LinearLayout
    private lateinit var startButton: Button
    private lateinit var firstBox: TextView
    private lateinit var secondBox: TextView
    private lateinit var instructions: TextView
    private lateinit var score: TextView
    private lateinit var strikes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = findViewById(R.id.main)
        startButton = findViewById(R.id.start_button)
        firstBox = findViewById(R.id.first_box)
        secondBox = findViewById(R.id.second_box)
        instructions = findViewById(R.id.instruction_TextView)
        score = findViewById(R.id.score_TextView)
        strikes = findViewById(R.id.strike_TextView)

        start()

    }

    private fun randNum() {
        firstNum = (Random.nextInt(100) + 1)
        secondNum = (Random.nextInt(100) + 1)
        while (firstNum == secondNum) {
            secondNum = (Random.nextInt(100) + 1)
        }
        firstBox.text = firstNum.toString()
        secondBox.text = secondNum.toString()
    }

    private fun checkLargerNum(): Int {
        if (firstNum > secondNum) {
            return firstNum
        }
        return secondNum
    }

    private fun start() {
        startButton.setOnClickListener {
            instructions.text = "Tap the larger number!"
            startButton.text = "Restart"
            randNum()
        }

        firstBox.setOnClickListener {
            if (checkLargerNum() == firstNum) {
                mainView.setBackgroundColor(Color.parseColor("#8ACE00"))
                scoreCount++
                score.text = "Score: $scoreCount"
            } else {
                mainView.setBackgroundColor(Color.parseColor("#ED4337"))
                strikeCount++
                score.text = "Strike: $strikeCount"
            }
        }


    }


}