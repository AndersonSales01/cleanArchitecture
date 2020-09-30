package com.accenture.cleanarchitecture

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.accenture.cleanarchitecture.data.repository.RepoRepository
import com.accenture.cleanarchitecture.domain.entities.AuthorDTO
import com.accenture.cleanarchitecture.domain.entities.RepositoryDTO
import com.accenture.cleanarchitecture.presentation.entities.Repository
import com.accenture.cleanarchitecture.presentation.ui.repository.viewmodel.RepositoryViewModel
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    lateinit var repoRepository: RepoRepository
    lateinit var repositoryViewModel: RepositoryViewModel

    @Mock
    lateinit var observer: Observer<List<Repository>>

    @Mock
    lateinit var stateObserver: Observer<List<Repository>>


    val dispatcher = TestCoroutineDispatcher()

   // @get:Rule
  //  var mainCoroutineRule = MainCoroutineRule()

//    // Set the main coroutines dispatcher for unit testing
//    @get:Rule
//    var coroutinesRule = MainCoroutineRule()


//    @get:Rule
//    val rule = InstantTaskExecutorRule()
//
//    @get:Rule
//    var coroutinesTestRule = MainCo()

    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner

    @Before
    open fun setUp() {
        lifeCycleTestOwner = LifeCycleTestOwner()
        lifeCycleTestOwner.onCreate()
        repoRepository = Mockito.mock(RepoRepository::class.java)
        repositoryViewModel = RepositoryViewModel()
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetRepository() {

        var result: List<RepositoryDTO> = ArrayList()
        runBlocking {
            withContext(Dispatchers.IO) {
                `when`(repoRepository.getListRepositoriesRemote(2)).thenReturn(mock())
                result = repoRepository.getListRepositoriesRemote(2)
                Mockito.verify(repoRepository, Mockito.times(1)).getListRepositoriesRemote(2)

            }
        }


        assertEquals(2, result.size)

    }

    @Test
    fun testGetRepositoryInViewModel() {
        lateinit var list: List<Repository>
       dispatcher.runBlockingTest {
          //  withContext(Dispatchers.IO) {
                repositoryViewModel.getRepositories()
                repositoryViewModel.listRepositoriesResult().observeForever {
                    list = it
                   // Log.d("ListRepo", "testGetRepositoryInViewModel-> " + list.size)

            //    }


            }
        }



        assertEquals(29, list.size)

        // list = repositoryViewModel.listRepositoriesResult().observe(lifeCycleTestOwner)
    }


    private fun mock(): List<RepositoryDTO> {
        var list = ArrayList<RepositoryDTO>()
        list.add(
            RepositoryDTO(
                "Anderson",
                "Anderson Carlos",
                "Dev Android",
                10,
                10,
                AuthorDTO("anderson", "")
            )
        )

        list.add(
            RepositoryDTO(
                "Anderson2",
                "Anderson Carlos2",
                "Dev Android2",
                20,
                20,
                AuthorDTO("anderson2", "")
            )
        )

        return list

    }

}