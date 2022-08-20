package com.example.basicviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Radio logic
        val rgFoodType = findViewById<RadioGroup>(R.id.rgFoodType)
        rgFoodType.setOnCheckedChangeListener { _, _ ->
            val cbSausage: CheckBox = findViewById(R.id.cbSausage)
            cbSausage.isEnabled = rgFoodType.checkedRadioButtonId != R.id.rbVeg
            if (rgFoodType.checkedRadioButtonId == R.id.rbVeg) cbSausage.isChecked = false
        }
//        Order logic
        val btnOrder: Button = findViewById(R.id.btnOrder)
        btnOrder.setOnClickListener {
            rgFoodType.checkedRadioButtonId.let {
                if (it == -1) {
                    Toast.makeText(this,"Select food type", Toast.LENGTH_SHORT).show()
                } else {
                    val foodType: String = findViewById<RadioButton>(rgFoodType.checkedRadioButtonId).text.toString()
                    val toppings = checkToppings(foodType)
                    val order = if (foodType == "Veg") "v$toppings" else "n$toppings"

                    Intent(this, OrderActivity::class.java).also {
                        it.putExtra(OrderActivity.EXTRA_ORDER, order)
                        startActivity(it)
                    }

                }
            }



        }

    }

    fun checkToppings(foodType: String): String {
        val cbPaneer: CheckBox = findViewById(R.id.cbPaneer)
        val cbOlives: CheckBox = findViewById(R.id.cbOlives)
        val cbCheese: CheckBox = findViewById(R.id.cbCheese)
        var orderString = ""

        if (foodType != "Veg") {
            val cbSausage: CheckBox = findViewById(R.id.cbSausage)
            orderString = if (cbSausage.isChecked) cbSausage.text[0].toString() else ""
        }
        orderString += if (cbPaneer.isChecked) cbPaneer.text[0].toString() else ""
        orderString += if (cbOlives.isChecked) cbOlives.text[0].toString() else ""
        orderString += if (cbCheese.isChecked) cbCheese.text[0].toString() else ""

        return orderString.lowercase()
    }


}