package com.accenture.cleanarchitecture.app.features.repository.ui.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.accenture.cleanarchitecture.R
import com.accenture.cleanarchitecture.app.common.BaseActivity
import com.accenture.cleanarchitecture.app.features.repository.ui.adapter.RepositoryAdapter
import com.accenture.cleanarchitecture.app.features.repository.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : BaseActivity() {

    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var manager: LinearLayoutManager
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        initialize()

    }

    override fun initialize() {
        initViews()

        repositoryViewModel = ViewModelProviders.of(this)
            .get(RepositoryViewModel::class.java)

        repositoryViewModel.getRepositories()

        observables()
    }

    override fun initViews() {
        repositoryAdapter = RepositoryAdapter(this)

        manager = LinearLayoutManager(this)

        list_repositories.layoutManager = manager
        list_repositories.adapter = repositoryAdapter
    }

    override fun observables() {
        repositoryViewModel.listRepositoriesResult().observe(this, Observer { listRepertories ->
            repositoryAdapter.loadRepository(listRepertories)
        })
    }


}
