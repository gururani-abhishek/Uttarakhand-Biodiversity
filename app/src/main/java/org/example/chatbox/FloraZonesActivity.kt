package org.example.chatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class FloraZonesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flora_zones)


        Toast.makeText(this, "FloraZonesActivity", Toast.LENGTH_SHORT).show()
    }
}