package com.example.prac1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class result : AppCompatActivity() {

    private lateinit var figureTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var shapeImageView: ImageView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        figureTextView = findViewById(R.id.figureTextView)
        resultTextView = findViewById(R.id.resultTextView)
        shapeImageView = findViewById(R.id.shapeImageView)
        backButton = findViewById(R.id.backButton)

        val shape = intent.getStringExtra("shape")
        val result = intent.getStringExtra("result")
        val imageResId = intent.getIntExtra("imageResId", R.drawable.triangle) // Получаем ресурс изображения

        figureTextView.text = shape
        resultTextView.text = "Результат: $result"
        shapeImageView.setImageResource(imageResId)

        backButton.setOnClickListener {
            val intent = Intent(this, register::class.java) // Возвращаемся на окно регистрации
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Удаляем предыдущие активности из стека
            startActivity(intent)
        }
    }
}
