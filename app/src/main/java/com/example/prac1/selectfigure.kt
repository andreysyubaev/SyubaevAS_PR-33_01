package com.example.prac1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class selectfigure : AppCompatActivity() {

    private lateinit var shapeSpinner: Spinner
    private lateinit var shapeImageView: ImageView
    private lateinit var valueEditText: EditText
    private lateinit var calculateButton: Button
    private var shapeImageResId: Int = R.drawable.triangle // Начальное изображение

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectfigure)

        shapeSpinner = findViewById(R.id.shapeSpinner)
        shapeImageView = findViewById(R.id.shapeImageView)
        valueEditText = findViewById(R.id.valueEditText)
        calculateButton = findViewById(R.id.calculateButton)

        shapeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        shapeImageView.setImageResource(R.drawable.triangle)
                        shapeImageResId = R.drawable.triangle
                    }
                    1 -> {
                        shapeImageView.setImageResource(R.drawable.circle)
                        shapeImageResId = R.drawable.circle
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Ничего не делаем
            }
        }

        calculateButton.setOnClickListener {
            val selectedShape = shapeSpinner.selectedItem.toString()
            val value = valueEditText.text.toString().toDoubleOrNull()

            if (value != null) {
                val result1 = when (selectedShape) {
                    "Треугольник" -> 2 * value + value // Пример расчета
                    "Круг" -> value / (2 * Math.PI) // Пример расчета
                    else -> 0.0
                }

                val intent = Intent(this, result::class.java)
                intent.putExtra("shape", selectedShape)
                intent.putExtra("result", result1.toString())
                intent.putExtra("imageResId", shapeImageResId) // Передаем ресурс изображения
                startActivity(intent)
            } else {
                valueEditText.error = "Введите корректное значение"
            }
        }
    }
}