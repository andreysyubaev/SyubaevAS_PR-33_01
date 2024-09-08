package com.example.prac1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class selectfigure : AppCompatActivity() {

    private lateinit var shapeSpinner: Spinner
    private lateinit var shapeImageView: ImageView
    private lateinit var valueAEditText: EditText
    private lateinit var valueBEditText: EditText
    private lateinit var input_b_container: LinearLayout
    private lateinit var calculateButton: Button
    private var shapeImageResId: Int = R.drawable.triangle // Начальное изображение

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectfigure)

        shapeSpinner = findViewById(R.id.shapeSpinner)
        shapeImageView = findViewById(R.id.shapeImageView)
        valueAEditText = findViewById(R.id.valueAEditText)
        valueBEditText = findViewById(R.id.valueBEditText)
        input_b_container = findViewById(R.id.input_b_container)
        calculateButton = findViewById(R.id.calculateButton)

        shapeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> { // Треугольник
                        shapeImageView.setImageResource(R.drawable.triangle)
                        shapeImageResId = R.drawable.triangle
                        input_b_container.visibility = View.VISIBLE
                    }
                    1 -> { // Круг
                        shapeImageView.setImageResource(R.drawable.circle)
                        shapeImageResId = R.drawable.circle
                        input_b_container.visibility = View.GONE
                    }

                    1 -> { // Прямоугольник
                        shapeImageView.setImageResource(R.drawable.circle)
                        shapeImageResId = R.drawable.rectangle
                        input_b_container.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Ничего не делаем
            }
        }

        calculateButton.setOnClickListener {
            val selectedShape = shapeSpinner.selectedItem.toString()
            val a = valueAEditText.text.toString().toDoubleOrNull()
            val b = valueBEditText.text.toString().toDoubleOrNull()

            if (a != null) {
                val result1 = when (selectedShape) {
                    "Треугольник" -> {
                        if (b != null) {
                            2 * a + b
                        } else {
                            valueBEditText.error = "Введите значение b"
                            return@setOnClickListener
                        }
                    }
                    "Круг" -> a / (2 * Math.PI)
                    "Прямоугольник" -> {
                        if (b != null) {
                            a * b
                        } else {
                            valueBEditText.error = "Введите значение b"
                            return@setOnClickListener
                        }
                    }
                    else -> 0.0
                }

                // Округляем результат до сотых
                val roundedResult = String.format("%.2f", result1)

                // Переход на окно результатов
                val intent = Intent(this, result::class.java)
                intent.putExtra("shape", selectedShape)
                intent.putExtra("result", roundedResult)
                intent.putExtra("imageResId", shapeImageResId) // Передаем ресурс изображения
                startActivity(intent)
            } else {
                valueAEditText.error = "Введите корректное значение a"
            }
        }
    }
}