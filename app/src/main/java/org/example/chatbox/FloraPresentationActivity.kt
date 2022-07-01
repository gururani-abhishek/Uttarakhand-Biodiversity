package org.example.chatbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class FloraPresentationActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flora_presentation)

        val valley = findViewById<Button>(R.id.valley)
        valley.setOnClickListener {
            val valleyOfFlowersActivityIntent = Intent(this, ValleyOfFlowersActivity::class.java)
            startActivity(valleyOfFlowersActivityIntent)
        }

        val status = findViewById<Button>(R.id.status)
        status.setOnClickListener {
            val statusFloraFaunaActivityIntent = Intent(this, StatusFloraFaunaActivity::class.java)
            startActivity(statusFloraFaunaActivityIntent)
        }

        val zones = findViewById<Button>(R.id.zones)
        zones.setOnClickListener {
            val floraZonesActivityIntent = Intent(this, FloraZonesActivity::class.java)
            startActivity(floraZonesActivityIntent)
        }
    }

}