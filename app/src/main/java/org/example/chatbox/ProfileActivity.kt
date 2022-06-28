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
import java.io.FileReader

private const val Tag : String = "document"
class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val desc = findViewById<TextView>(R.id.desc)
        val db = Firebase.firestore
        val auth = Firebase.auth
        val currentUserId = auth.currentUser!!.uid
        db.collection("profile").document(currentUserId).get()
            .addOnSuccessListener { document ->
                if(document != null) {
                    desc.text = document.getString("desc")
                }
            }
            .addOnFailureListener{ exception ->
                Log.d(Tag, "get failed with ", exception)
            }

        val name = findViewById<TextView>(R.id.name)
        val email = findViewById<TextView>(R.id.email)
        val mobileNo = findViewById<TextView>(R.id.phoneNumber)
        val imageView = findViewById<ImageView>(R.id.profilePic)
        db.collection("profile").document(currentUserId).get()
            .addOnSuccessListener{ document ->
                if(document !=  null)  {
                    name.text = document.getString("name")
                    mobileNo.text = document.getString("mobileNo")
                    email.text = document.getString("email")
                    Glide.with(this)
                        .load(document.getString("imageUrl"))
                        .circleCrop()
                        .into(imageView)
                } else {
                    Log.d(Tag, "no such document!")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(Tag, "get failed with ", exception)
            }



    }
}