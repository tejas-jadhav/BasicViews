package com.example.basicviews

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class OrderActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ORDER: String = "extra_order"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val order = intent.getStringExtra(EXTRA_ORDER)
        val pizzaId = bakePizza(order ?: "voc")

        findViewById<ImageView>(R.id.ivPizza).also { img ->
            img.setImageResource(pizzaId)
        }

        val btnBurp: Button = findViewById(R.id.btnBurp)
        btnBurp.setOnClickListener {
            Toast(applicationContext).apply {
                duration = Toast.LENGTH_SHORT
                view = layoutInflater.inflate(R.layout.custom_toast, findViewById(R.id.clCustomToast), false)
                show()
            }
            finish()
        }
    }

    fun bakePizza(order: String): Int {
        val pizzaId = when (order) {
            "ns" -> R.drawable.ns
            "nsoc" -> R.drawable.nsoc
            "nspoc" -> R.drawable.nspoc
            "vc" -> R.drawable.vc
            "vo" -> R.drawable.vo
            "voc" -> R.drawable.voc
            "vp" -> R.drawable.vp
            "vpoc" -> R.drawable.vpoc
            else -> R.drawable.nsoc
        }
        return pizzaId
    }
}