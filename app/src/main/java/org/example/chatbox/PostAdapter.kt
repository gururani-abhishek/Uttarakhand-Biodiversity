package org.example.chatbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.example.chatbox.models.Post

class PostAdapter(options: FirestoreRecyclerOptions<Post>, val listener: IPostAdapter) : FirestoreRecyclerAdapter<Post, PostAdapter.PostViewHolder>(options) {

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userImage: ImageView = itemView.findViewById(R.id.userImage)
        val userName: TextView = itemView.findViewById(R.id.userName)
        val createdAt: TextView = itemView.findViewById(R.id.createdAt)
        val postText: TextView = itemView.findViewById(R.id.postTitle)
        val likeCount: TextView = itemView.findViewById(R.id.likeCount)
        val likeButton: ImageView = itemView.findViewById(R.id.likeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val viewHolder =  PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,  parent, false))
        viewHolder.likeButton.setOnClickListener {
            listener.onLikeClicked(snapshots.getSnapshot(viewHolder.adapterPosition).id)
        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
        holder.postText.text = model.text
        holder.userName.text = model.createdBy.displayName
        holder.likeCount.text = model.likedBy.size.toString()
        Glide.with(holder.userImage.context).load(model.createdBy.imageUrl).circleCrop().into(holder.userImage)
        holder.createdAt.text = Utils.getTimeAgo(model.createdAt)

        val auth = Firebase.auth
        val currentUserId = auth.currentUser!!.uid
        val isLiked = model.likedBy.contains(currentUserId)
        if(isLiked) {
            holder.likeButton.setImageDrawable(ContextCompat.getDrawable(holder.likeButton.context, R.drawable.ic_liked))
        }else {
            holder.likeButton.setImageDrawable(ContextCompat.getDrawable(holder.likeButton.context, R.drawable.ic_unliked))
        }
    }

}

interface IPostAdapter {
    fun onLikeClicked(postId: String)
}