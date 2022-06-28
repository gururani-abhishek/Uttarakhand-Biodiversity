package org.example.chatbox.daos

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.example.chatbox.models.User

class UserDao {
    private val db = Firebase.firestore
    private val userCollection = db.collection("users")


    fun addUser(user : User?) {
        //user : User?  user is of type nullable now.
        //user?.let  if user will be non-null then only it'll go inside the block.
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                userCollection.document(user.uid).set(it)
            }
        }
    }

    fun getUserById(uId : String): Task<DocumentSnapshot> {
        return userCollection.document(uId).get()
    }

}