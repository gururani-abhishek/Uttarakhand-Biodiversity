package org.example.chatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.example.chatbox.models.Animals

class FaunaPresentationActivity : AppCompatActivity(), FaunaViewAdaptor.IFaunaAdaptor {
    private lateinit var adaptor: FaunaViewAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fauna_presentation)

        Toast.makeText(this, "Fauna!!", Toast.LENGTH_SHORT).show()

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val db = Firebase.firestore
        val faunaCollection = db.collection("fauna")
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Animals>().setQuery(faunaCollection, Animals::class.java).build()
        adaptor = FaunaViewAdaptor(recyclerViewOptions, this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adaptor
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onStart() {
        super.onStart()
        adaptor.startListening()
    }

    override fun onStop() {
        super.onStop()
        adaptor.stopListening()
    }

    override fun onFavClicked(animalId: String) {
        GlobalScope.launch {
            val auth = Firebase.auth
            val currentUserId = auth.currentUser!!.uid
            val db = Firebase.firestore
            val faunaCollection = db.collection("fauna")
            val animal = faunaCollection.document(animalId).get().await().toObject(Animals::class.java)!!
            val isFav = animal.favBy.contains(currentUserId)

            if(isFav) {
                animal.favBy.remove(currentUserId)
            } else {
                animal.favBy.add(currentUserId)
            }

            faunaCollection.document(animalId).set(animal)

        }

    }
}