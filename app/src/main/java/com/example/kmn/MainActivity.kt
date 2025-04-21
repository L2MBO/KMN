package com.example.kmn

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var playerChoice: String
    private val choices = listOf("Камень", "Ножницы", "Бумага", "Ящерица", "Спок")

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttons = mapOf(
            "Камень" to findViewById(R.id.btnRock),
            "Ножницы" to findViewById(R.id.btnScissors),
            "Бумага" to findViewById(R.id.btnPaper),
            "Ящерица" to findViewById(R.id.btnLizard),
            "Спок" to findViewById<Button>(R.id.btnSpock)
        )

        val resultText = findViewById<TextView>(R.id.resultText)
        val playButton = findViewById<Button>(R.id.btnPlay)
        val playerImage = findViewById<ImageView>(R.id.playerChoiceImage)
        val computerImage = findViewById<ImageView>(R.id.computerChoiceImage)
        playerChoice = ""

        val choiceImages = mapOf(
            "Камень" to R.drawable.rock,
            "Ножницы" to R.drawable.scissors,
            "Бумага" to R.drawable.paper,
            "Ящерица" to R.drawable.lizard,
            "Спок" to R.drawable.spock
        )

        buttons.forEach { (choice, button) ->
            button.setOnClickListener {
                playerChoice = choice
                buttons.values.forEach { it.alpha = 1.0f }
                button.alpha = 0.5f
                playerImage.setImageResource(choiceImages[choice]!!)
            }
        }

        playButton.setOnClickListener {
            if (playerChoice.isEmpty()) {
                Toast.makeText(this, "Делает выбор:", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val computerChoice = choices.random()
            computerImage.setImageResource(choiceImages[computerChoice]!!)
            val winner = determineWinner(playerChoice, computerChoice)
            resultText.text = "Игрок: $playerChoice\nБот: $computerChoice\nРезультат: $winner"
        }
    }

    private fun determineWinner(player: String, computer: String): String {
        if (player == computer) return "Ничья!"

        return when {
            (player == "Ножницы" && (computer == "Бумага" || computer == "Ящерица")) ||
                    (player == "Бумага" && (computer == "Камень" || computer == "Спок")) ||
                    (player == "Камень" && (computer == "Ящерица" || computer == "Ножницы")) ||
                    (player == "Ящерица" && (computer == "Спок" || computer == "Бумага")) ||
                    (player == "Спок" && (computer == "Ножницы" || computer == "Камень"))
                -> "Ты победил!"
            else -> "Бот победил!"
        }
    }
}