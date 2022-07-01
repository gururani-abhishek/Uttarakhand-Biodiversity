package org.example.chatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ValleyOfFlowersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valley_of_flowers)

        Toast.makeText(this, "ValleyOfFlowersActivity", Toast.LENGTH_SHORT).show()
    }
}