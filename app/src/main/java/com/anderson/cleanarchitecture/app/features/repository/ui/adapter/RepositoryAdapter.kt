package com.anderson.cleanarchitecture.app.features.repository.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anderson.cleanarchitecture.R
import com.anderson.cleanarchitecture.app.features.repository.ui.Router
import com.anderson.cleanarchitecture.domain.entities.Repository
import com.anderson.cleanarchitecture.app.features.repository.ui.viewholder.ViewHolderRepository

class RepositoryAdapter (private val context: Context, private val router: Router) : RecyclerView.Adapter<ViewHolderRepository>() {

    private var listRepository: List<Repository> = mutableListOf()

    fun loadRepository(listRepository: List<Repository>) {
        this.listRepository = listRepository
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRepository {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_repository, parent, false)
        return ViewHolderRepository(
            view,
            context, router
        )
    }

    override fun getItemCount(): Int {
        return listRepository.size
    }

    override fun onBindViewHolder(holder: ViewHolderRepository, position: Int) {
        val repository = listRepository[position]
        holder?.bindView(repository)
    }
}