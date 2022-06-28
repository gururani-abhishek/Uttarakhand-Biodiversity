package org.example.chatbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

class AcademicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_academics)
    }

    fun GenerateCard(view: android.view.View) {
            val inputSem = findViewById<EditText>(R.id.inputSem).text.toString().toInt()
            if(inputSem <= 4) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(ResultActivity.NameKey, inputSem)
                startActivity(intent)
            }else {
                Toast.makeText(this, "not available", Toast.LENGTH_SHORT).show()
            }
        }

}