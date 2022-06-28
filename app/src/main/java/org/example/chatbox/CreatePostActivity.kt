package org.example.chatbox

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.example.chatbox.daos.PostDao

class CreatePostActivity : AppCompatActivity() {
    private lateinit var postDao: PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        val postButton = findViewById<Button>(R.id.postButton)
        postDao = PostDao()
        postButton.setOnClickListener {
            val postInput = findViewById<EditText>(R.id.postInput)
            val input = postInput.text.toString().trim()

            if(input.isNotEmpty()) {
                postDao.addPost(input)
                finish()
            }
        }
    }
}