package com.example.okhttpissuelistapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.okhttpissuelistapp.R
import com.example.okhttpissuelistapp.remote.Comment

class CommentListAdapter:RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>() {

    private var commentList = emptyList<Comment>()

    fun updateCommentList( comments:List<Comment>){
        commentList = comments
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.comment_list_item,parent,false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList.get(position))
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    class CommentViewHolder(view: View):RecyclerView.ViewHolder(view){

            val commentUserNameTv = view.findViewById<TextView>(R.id.comment_user_name_tv)
            val commentCreatedAtTv = view.findViewById<TextView>(R.id.comment_creation_date_tv)
            val commentBodyTv = view.findViewById<TextView>(R.id.comment_body_tv)
            val comment_avatar = view.findViewById<ImageView>(R.id.comment_user_avatar)

        fun bind(comment:Comment){
            commentBodyTv.text = comment.body
            commentUserNameTv.text = comment.user.userName
            commentCreatedAtTv.text = comment.createdAt
            val avatarUri = comment.user.avatarUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(comment_avatar.context).load(avatarUri).circleCrop().into(comment_avatar)
        }
    }
}