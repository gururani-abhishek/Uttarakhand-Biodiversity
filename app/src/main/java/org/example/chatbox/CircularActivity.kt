package org.example.chatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val Tag : String = "document"
class CircularActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular)
        val circular = findViewById<ImageView>(R.id.noticeLatest)
        val db = Firebase.firestore
        db.collection("circular").document("latest").get()
            .addOnSuccessListener { document ->
                if(document != null) {
                    Glide.with(this)
                        .load(document.getString("noticeUrl"))
                        .into(circular)
                }
            }
            .addOnFailureListener {exception ->
                Log.d(Tag, "get failed ", exception)
            }
    }
}