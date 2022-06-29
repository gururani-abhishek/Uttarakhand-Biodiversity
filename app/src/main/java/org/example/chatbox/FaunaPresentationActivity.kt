package org.example.chatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class FaunaPresentationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fauna_presentation)

        Toast.makeText(this, "Fauna!!", Toast.LENGTH_SHORT).show()
    }
}