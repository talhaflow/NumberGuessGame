package com.talhakara.number_guess_game

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var guessEditText: EditText
    private lateinit var submitButton: Button

    private var secretNumber = 0
    private var attempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessEditText = findViewById(R.id.editTextNumber)
        submitButton = findViewById(R.id.button)

        // Oyunu başlat
        startNewGame()

        submitButton.setOnClickListener {
            val userGuess = guessEditText.text.toString().toIntOrNull()

            if (userGuess != null) {
                attempts++
                when {
                    userGuess == secretNumber -> {
                        showResultDialog(getString(R.string.congratulations, secretNumber, attempts))
                        startNewGame()
                    }
                    userGuess < secretNumber -> showResultDialog(getString(R.string.guess_higher))
                    else -> showResultDialog(getString(R.string.guess_lower))
                }
            } else {
                showResultDialog(getString(R.string.invalid_guess))
            }

            guessEditText.text.clear()
        }
    }

    private fun startNewGame() {
        secretNumber = Random.nextInt(1, 101)
        attempts = 0
    }

    private fun showResultDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("Tamam") { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }
}
