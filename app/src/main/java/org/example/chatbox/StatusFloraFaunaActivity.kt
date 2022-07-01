package org.example.chatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

class StatusFloraFaunaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_flora_fauna)

        val link = "https://user-images.githubusercontent.com/59947871/176766971-cb2a2c45-1616-4777-a8e5-399aa9231937.png"
        Toast.makeText(this, "StatusFloraFaunaActivity", Toast.LENGTH_SHORT).show()
        val table = findViewById<ImageView>(R.id.table)
        Glide.with(this)
            .load(link)
            .into(table)

    }
}