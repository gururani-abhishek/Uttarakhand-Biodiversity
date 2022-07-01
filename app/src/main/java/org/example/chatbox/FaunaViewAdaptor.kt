package org.example.chatbox

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.example.chatbox.models.Animals
import org.w3c.dom.Text

class FaunaViewAdaptor(options: FirestoreRecyclerOptions<Animals>, val listener: IFaunaAdaptor) : FirestoreRecyclerAdapter<Animals, FaunaViewAdaptor.FaunaViewHolder>(
    options
) {
    class FaunaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val animalImage: ImageView = itemView.findViewById(R.id.animalImage)
        val sciName: TextView = itemView.findViewById(R.id.sciName)
        val favCount: TextView = itemView.findViewById(R.id.favCount)
        val localName: TextView = itemView.findViewById(R.id.localName)
        val fav: ImageView = itemView.findViewById(R.id.fav)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaunaViewHolder {
        val viewHolder =  FaunaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_fauna, parent, false))
        viewHolder.fav.setOnClickListener {
            listener.onFavClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FaunaViewHolder, position: Int, model: Animals) {
        holder.sciName.text = model.sciName
        holder.localName.text = model.localName
        Glide.with(holder.animalImage.context)
            .load(model.animalImage)
            .fitCenter()
            .transform(RoundedCorners(30))
            .into(holder.animalImage)

        holder.favCount.text = model.favBy.size.toString()

        val auth = Firebase.auth
        val currentUserId = auth.currentUser!!.uid
        //to show, whether fav or not
        val isFav = model.favBy.contains(currentUserId)
        if(isFav) {
            holder.fav.setImageDrawable(ContextCompat.getDrawable(holder.fav.context,R.drawable.ic_favourite))
        } else {
            holder.fav.setImageDrawable(ContextCompat.getDrawable(holder.fav.context, R.drawable.ic_unfavourite))
        }
    }

    interface IFaunaAdaptor {
        fun onFavClicked(animalId : String);
    }
}