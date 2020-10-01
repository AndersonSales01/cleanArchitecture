package com.accenture.cleanarchitecture.app.features.repository.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.cleanarchitecture.R
import com.accenture.cleanarchitecture.domain.entities.Repository
import com.accenture.cleanarchitecture.app.features.repository.ui.viewholder.ViewHolderRepository

class RepositoryAdapter (private val context: Context) : RecyclerView.Adapter<ViewHolderRepository>() {

    private var listRepository: List<Repository> = mutableListOf()

    fun loadRepository(listRepository: List<Repository>) {
        this.listRepository = listRepository
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRepository {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_repository, parent, false)
        return ViewHolderRepository(
            view,
            context
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