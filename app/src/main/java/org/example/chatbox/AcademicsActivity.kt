package org.example.chatbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class AcademicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_academics)

        val floraBtn = findViewById<ImageView>(R.id.flora)
        val faunaBtn = findViewById<ImageView>(R.id.fauna)

        floraBtn.setOnClickListener {
            val floraActivityIntent = Intent(this, FloraPresentationActivity::class.java)
            startActivity(floraActivityIntent)
        }

        faunaBtn.setOnClickListener {
            val faunaActivityIntent = Intent(this, FaunaPresentationActivity::class.java)
            startActivity(faunaActivityIntent)
        }
    }
}