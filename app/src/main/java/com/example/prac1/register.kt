package com.example.prac1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class register : AppCompatActivity() {

    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        loginEditText = findViewById(R.id.loginEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        registerButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (login.isEmpty() || password.isEmpty()) {
                showAlertDialog("Введите логин и пароль")
            } else {
                val savedLogin = sharedPreferences.getString("login", null)
                val savedPassword = sharedPreferences.getString("password", null)

                if (savedLogin == null || savedPassword == null) {
                    // Первый вход
                    if (login == "ects" && password == "ects2023") {
                        saveCredentials(login, password)
                        navigateToSecondScreen()
                    } else {
                        showAlertDialog("Неверный логин или пароль")
                    }
                } else {
                    // Повторный вход
                    if (login == savedLogin && password == savedPassword) {
                        navigateToSecondScreen()
                    } else {
                        showAlertDialog("Неверный логин или пароль")
                    }
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun saveCredentials(login: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("login", login)
        editor.putString("password", password)
        editor.apply()
    }

    private fun navigateToSecondScreen() {
        val intent = Intent(this, selectfigure::class.java)
        startActivity(intent)
    }

    private fun showAlertDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}