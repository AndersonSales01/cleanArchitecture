package com.anderson.cleanarchitecture.app.features.pullrequest.ui.viewholder

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.anderson.cleanarchitecture.domain.entities.PullRequest
import com.anderson.cleanarchitecture.util.DateFomatUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_pull_request.view.*


class ViewHolderPullRequest (itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {


    fun bindView(pullRequest: PullRequest) {

        val txtTitle= itemView.title_pull_request
        val txtBody = itemView.body_pull_request
        val txtLogin = itemView.login_user
        val txtDataCreate = itemView.data_create


        txtTitle.text = pullRequest.title
        txtLogin.text = pullRequest.user.name_user
        txtBody.text = pullRequest.body
        txtDataCreate.text = DateFomatUtil.dateFormat(pullRequest.dataCreatePullRequest)

        itemView.setOnClickListener {

            openPageBrowser(pullRequest.urlPullRequest)
        }

        getImagem(pullRequest.user.avatarURL)


    }

    private fun getImagem(urlImg: String) {
        Picasso
                .with(context)
                .load(urlImg)
                .into(itemView.ic_user)
    }

    private fun openPageBrowser (urls: String) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        context.startActivity(intents)
    }

}