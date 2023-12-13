package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var balance = 100.0 // Initial balance
    private var incorrectAttempts = 0
    private var passwordEditText: EditText? = null
    private var depositEditText: EditText? = null
    private var balanceTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        depositEditText = findViewById(R.id.depositEditText)
        balanceTextView = findViewById(R.id.balanceTextView)
    }

    fun authenticate(view: View?) {
        val enteredPassword = passwordEditText!!.text.toString()
        if (enteredPassword == CORRECT_PASSWORD) {
            setContentView(R.layout.main_screen)
            balanceTextView = findViewById(R.id.balanceTextView)
            updateBalanceText()
        } else {
            incorrectAttempts++
            Toast.makeText(
                this,
                "Incorrect password. Attempts: $incorrectAttempts",
                Toast.LENGTH_SHORT
            ).show()
            if (incorrectAttempts >= 3) {
                finish() // Close the app after 3 incorrect attempts (you might want to implement a more secure mechanism)
            }
        }
    }

    fun deposit(view: View?) {
        val depositAmountStr = depositEditText!!.text.toString()
        if (!depositAmountStr.isEmpty()) {
            val depositAmount = depositAmountStr.toDouble()
            balance += depositAmount
            updateBalanceText()
        }
    }

    private fun updateBalanceText() {
        balanceTextView!!.text = String.format("Balance: $%.2f", balance)
    }

    companion object {
        private const val CORRECT_PASSWORD = "1234" // Change this to your desired password
    }
}
