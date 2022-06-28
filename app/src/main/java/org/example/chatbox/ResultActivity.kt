package org.example.chatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val Tag : String = "document"
class ResultActivity : AppCompatActivity() {
    companion object{
        const val NameKey = "NameKey"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val name = intent.getIntExtra(NameKey, 1)
        val result = findViewById<ImageView>(R.id.result)
        val db =  Firebase.firestore
        val auth = Firebase.auth
        val currentUserId = auth.currentUser!!.uid
        db.collection("profile").document(currentUserId).get()
            .addOnSuccessListener { document ->
                if(document != null) {
                    Glide.with(this)
                        .load(document.getString("res$name"))
                        .into(result)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(Tag, "failed get", exception)
            }
    }
}