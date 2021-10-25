package com.example.okhttpissuelistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.okhttpissuelistapp.R
import com.example.okhttpissuelistapp.domain.OkHttpIssue
import com.example.okhttpissuelistapp.remote.OkHttpIssueNetwork
import com.example.okhttpissuelistapp.viewmodel.CommentListViewModel

class IssueDetailFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_issue_detail,container,false)

        val viewModel = ViewModelProvider(this).get(CommentListViewModel::class.java)

        val commentRecyclerView = rootView.findViewById<RecyclerView>(R.id.comment_list_rv)
        val commentListAdapter = CommentListAdapter()
        commentRecyclerView.adapter = commentListAdapter

        viewModel.commentList.observe(viewLifecycleOwner,{
            it?.let {
                commentListAdapter.updateCommentList(it)
            }

        })

        val arguments = arguments
        val githubIssue = arguments!!.getParcelable<OkHttpIssue>("gitIssue")
        val issueInfoTextView = rootView.findViewById<TextView>(R.id.issue_info_tv)
        issueInfoTextView.text = githubIssue!!.body
        return rootView
    }
}