package com.example.okhttpissuelistapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.okhttpissuelistapp.R
import com.example.okhttpissuelistapp.viewmodel.OkHttpIssueViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IssueListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IssueListFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_issue_list, container, false)

        val viewModel = ViewModelProvider(requireActivity(),OkHttpIssueViewModel.Factory(requireActivity().application)).get(OkHttpIssueViewModel::class.java)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.issue_list_rv)
        val issueAdapter = IssueListAdapter(IssueListAdapter.ItemClickListener{
            viewModel.onListItemClicked(it)
        })
        recyclerView.adapter = issueAdapter


        viewModel.issuelist.observe(viewLifecycleOwner, Observer {
            issueAdapter.refreshIssueList(it)
        })

        viewModel.navigateToIssueNetworkDetail.observe(viewLifecycleOwner,{

            if (it!=null){
                val argument = Bundle()
                argument.putParcelable("gitIssue",it)
                this.findNavController().navigate(R.id.action_issue_list_to_issue_detail,argument)
                viewModel.navigatedToIssueDetail()
            }
        })
        return rootView
    }


}