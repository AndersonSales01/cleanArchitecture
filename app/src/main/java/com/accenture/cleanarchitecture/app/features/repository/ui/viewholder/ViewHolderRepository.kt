package com.accenture.cleanarchitecture.app.features.repository.ui.viewholder

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.accenture.cleanarchitecture.app.features.pullrequest.ui.view.PullRequestActivity
import com.accenture.cleanarchitecture.app.features.repository.ui.Router
import com.accenture.cleanarchitecture.constants.Constants
import com.accenture.cleanarchitecture.domain.entities.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_repository.view.*

class ViewHolderRepository(itemView: View, private val context: Context, private val router: Router) : RecyclerView.ViewHolder(itemView) {

    fun bindView(repository: Repository) {

        val txtRepositoryName = itemView.repo_name
        val txtLogin = itemView.login
         //val txtUserName = itemView.user_name
        val txtNumberStarts = itemView.number_stars
        val txtNumberForks = itemView.number_forks
        val txtDescription = itemView.description


        txtRepositoryName.text = repository.name
        txtLogin.text = repository.author.name
        // txtUserName.text = repository.author.name
        txtNumberStarts.text = repository.numberStarts.toString()
        txtNumberForks.text = repository.numberForks.toString()
        txtDescription.text = repository.description

        getImagem(repository.author.urlAvatar)

        itemView.setOnClickListener {
            router.toGoPullRequestScreen(repository)
        }
    }

    private fun getImagem(urlImg: String) {
        Picasso
            .with(context)
            .load(urlImg)
            .into(itemView.ic_user)
    }
}