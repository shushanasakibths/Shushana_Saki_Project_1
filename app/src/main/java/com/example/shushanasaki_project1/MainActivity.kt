package com.example.shushanasaki_project1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var scoreCount: Int = 0
    private var strikeCount: Int = 0
    private var firstNum = 0
    private var secondNum = 0
    private var isGameActive: Boolean = false
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
        initializeViews()
        resetScoreAndStrikes()

    }

    private fun initializeViews() {
        mainView = findViewById(R.id.main)
        startButton = findViewById(R.id.start_button)
        firstBox = findViewById(R.id.first_box)
        secondBox = findViewById(R.id.second_box)
        instructions = findViewById(R.id.instruction_TextView)
        score = findViewById(R.id.score_TextView)
        strikes = findViewById(R.id.strike_TextView)
        setClickListeners()
    }

    private fun setClickListeners() {
        startButton.setOnClickListener { startGame() }
        firstBox.setOnClickListener {
            if (isGameActive) onNumberTap(firstNum)
        }
        secondBox.setOnClickListener {
            if (isGameActive) onNumberTap(secondNum)
        }
    }

    private fun startGame() {
        instructions.text = getString(R.string.instruction_tap_larger)
        startButton.text = getString(R.string.restart_button)
        isGameActive = true
        resetScoreAndStrikes()
        generateRandomNumbers()
    }

    private fun resetScoreAndStrikes() {
        scoreCount = 0
        strikeCount = 0
        updateScoreAndStrikes()
        setTextColor(score, Color.BLACK)
        setTextColor(strikes, Color.BLACK)
        mainView.setBackgroundColor(Color.parseColor("#FFFDEF74"))
    }

    private fun generateRandomNumbers() {
        firstNum = Random.nextInt(1, 100)
        secondNum = Random.nextInt(1, 100)
        while (firstNum == secondNum) {
            secondNum = Random.nextInt(1, 100)
        }
        firstBox.text = firstNum.toString()
        secondBox.text = secondNum.toString()
    }

    private fun onNumberTap(tappedNum: Int) {
        if (scoreCount >= 10 || strikeCount >= 3) return

        if (tappedNum == checkLargerNum()) {
            scoreCount++
            setTextColor(score, Color.parseColor("#FFFDEF74"))
            mainView.setBackgroundColor(Color.GREEN)
        } else {
            strikeCount++
            setTextColor(strikes, Color.parseColor("#FFFDEF74"))
            mainView.setBackgroundColor(Color.RED)
        }

        updateScoreAndStrikes()
        checkGameStatus()
        if (isGameActive) {
            generateRandomNumbers()
        }
    }

    private fun checkLargerNum(): Int {
        return if (firstNum > secondNum) firstNum else secondNum
    }

    private fun updateScoreAndStrikes() {
        score.text = getString(R.string.score_string, scoreCount)
        strikes.text = getString(R.string.strike_string, strikeCount)
    }

    private fun checkGameStatus() {
        if (scoreCount >= 10) {
            showToast(getString(R.string.win_message))
            setEndGameColors(true)
        } else if (strikeCount >= 3) {
            showToast(getString(R.string.lose_message))
            setEndGameColors(false)
        }
    }


    private fun setEndGameColors(isWin: Boolean) {
        isGameActive = false
        if (isWin) {
            setTextColor(score, Color.GREEN)
            setTextColor(strikes, Color.BLACK)
        } else {
            setTextColor(score, Color.BLACK)
            setTextColor(strikes, Color.RED)
        }
        mainView.setBackgroundColor(Color.parseColor("#FFFDEF74"))
        instructions.text = getString(R.string.restart_instruction)
        firstBox.text = getString(R.string.first_box_text)
        secondBox.text = getString(R.string.second_box_text)
    }

    private fun setTextColor(textView: TextView, color: Int) {
        textView.setTextColor(color)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

