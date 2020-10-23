package com.accenture.cleanarchitecture.app.features.repository.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.cleanarchitecture.R
import com.accenture.cleanarchitecture.app.common.BaseActivity
import com.accenture.cleanarchitecture.app.features.login.ui.LoginActivity
import com.accenture.cleanarchitecture.app.features.pullrequest.ui.view.PullRequestActivity
import com.accenture.cleanarchitecture.app.features.repository.ui.Router
import com.accenture.cleanarchitecture.app.features.repository.ui.adapter.RepositoryAdapter
import com.accenture.cleanarchitecture.app.features.repository.viewmodel.RepositoryViewModel
import com.accenture.cleanarchitecture.constants.Constants
import com.accenture.cleanarchitecture.domain.entities.Repository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_repository.*
import javax.inject.Inject

class RepositoryActivity : BaseActivity(), Router {

   // private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var manager: LinearLayoutManager
    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var alertDialog: AlertDialog
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val repositoryViewModel: RepositoryViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[RepositoryViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        initialize()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.action_logout -> alertLogout()

        }

        return super.onOptionsItemSelected(item)

    }

    override fun initialize() {
        initViews()

        subComponent.inject(this)

        repositoryViewModel.getRepositories()

        observables()

        scrollInfinite()
    }

    override fun initViews() {
        repositoryAdapter = RepositoryAdapter(this,this)

        manager = LinearLayoutManager(this)

        list_repositories.layoutManager = manager
        list_repositories.adapter = repositoryAdapter
    }

    override fun observables() {
        repositoryViewModel.listRepositoriesResult().observe(this, Observer { listRepertories ->
            repositoryAdapter.loadRepository(listRepertories)
        })

        repositoryViewModel.showToastMessage().observe(this, Observer { message ->
            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        })
    }

    override fun toGoPullRequestScreen(repository: Repository) {
        val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra(Constants.NAME_OWNER, repository.author.name)
        intent.putExtra(Constants.NAME_REPOSITORY, repository.name)
        startActivity(intent)
    }

    private fun scrollInfinite() {

        list_repositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                repositoryViewModel.getMoreItems(manager.childCount, manager.findFirstVisibleItemPosition(), manager.itemCount)

            }

        })
    }

    private fun alertLogout(){
      val alert =  MaterialAlertDialogBuilder(this)
            .setMessage(resources.getString(R.string.alert_logout))

            .setNegativeButton(resources.getString(R.string.alert_logout_cancel)) { dialog, which ->
                alertDialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.alert_btn_logout)) { dialog, which ->
                toGoLoginActivity()
            }

        alertDialog = alert.create()
        alertDialog.show()
    }

    private fun toGoLoginActivity(){
        repositoryViewModel.userLogout()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
