package com.anderson.cleanarchitecture.app.features.pullrequest.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anderson.cleanarchitecture.R
import com.anderson.cleanarchitecture.app.features.pullrequest.ui.viewholder.ViewHolderPullRequest
import com.anderson.cleanarchitecture.domain.entities.PullRequest


class PullRequestAdapter( val context: Context) : RecyclerView.Adapter<ViewHolderPullRequest>() {

    private var listPullRequest: List<PullRequest> = mutableListOf()

    fun loadPullRequest(listPullRequest: List<PullRequest>) {
        this.listPullRequest = listPullRequest
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolderPullRequest, position: Int) {
        val pullRequest = listPullRequest[position]

        holder?.bindView(pullRequest)
    }

    override fun getItemCount(): Int {
        return listPullRequest.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPullRequest {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_pull_request, parent, false)
        return ViewHolderPullRequest(view,context)
    }
}