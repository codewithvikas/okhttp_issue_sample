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
import com.example.okhttpissuelistapp.domain.OkHttpIssue

class IssueListAdapter(val itemClickListener: ItemClickListener):RecyclerView.Adapter<IssueListAdapter.IssueViewHolder>() {

    private var issueList = emptyList<OkHttpIssue>()

    fun refreshIssueList(issues:List<OkHttpIssue>){
        issueList = issues
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.issue_list_item,parent,false)
        return IssueViewHolder(view,itemClickListener )
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
            holder.bind(issueList.get(position))
    }

    override fun getItemCount(): Int {
        return issueList.size
    }

    class ItemClickListener(val clickListener: (okHttpIssue:OkHttpIssue)->Unit){
        fun onClick(okHttpIssue: OkHttpIssue) = clickListener(okHttpIssue)
    }

    class IssueViewHolder(val view:View,val itemClickListener: ItemClickListener):RecyclerView.ViewHolder(view){

        val titleView = view.findViewById<TextView>(R.id.issue_title_tv)
        val bodyView = view.findViewById<TextView>(R.id.issue_body_tv)
        val userNameView = view.findViewById<TextView>(R.id.user_name_tv)
        val dateView = view.findViewById<TextView>(R.id.date_tv)
        val avatarImage = view.findViewById<ImageView>(R.id.avatar_iv)

        fun bind(okHttpIssue: OkHttpIssue){
            titleView.text = okHttpIssue.title
            bodyView.text = okHttpIssue.shortDescription
            userNameView.text = okHttpIssue.userName
            dateView.text = okHttpIssue.updatedAt

            val imageUrlString = okHttpIssue.avatarUrl

            val imageUri = imageUrlString.toUri().buildUpon().scheme("https").build()

            Glide.with(avatarImage.context).load(imageUri).circleCrop().into(avatarImage)

           view.setOnClickListener { itemClickListener.onClick(okHttpIssue) }

        }
    }
}