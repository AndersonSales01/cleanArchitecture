package com.accenture.cleanarchitecture

import com.accenture.cleanarchitecture.data.repository.RepoRepositoryImpl
import com.accenture.cleanarchitecture.app.features.repository.viewmodel.RepositoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    lateinit var repoRepositoryImpl: RepoRepositoryImpl
    lateinit var repositoryViewModel: RepositoryViewModel


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

//    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner

    @Before
    open fun setUp() {
//        lifeCycleTestOwner = LifeCycleTestOwner()
//        lifeCycleTestOwner.onCreate()
        repoRepositoryImpl = Mockito.mock(RepoRepositoryImpl::class.java)
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

//        var result: List<RepositoryDTO> = ArrayList()
//        runBlocking {
//            withContext(Dispatchers.IO) {
//                `when`(repoRepositoryImpl.getListRepositoriesRemote(2)).thenReturn(mock())
//                result = repoRepositoryImpl.getListRepositoriesRemote(2)
//                Mockito.verify(repoRepositoryImpl, Mockito.times(1)).getListRepositoriesRemote(2)
//
//            }
//        }
//
//
//        assertEquals(2, result.size)

    }

    @Test
    fun testGetRepositoryInViewModel() {
//        lateinit var list: List<Repository>
//       dispatcher.runBlockingTest {
//          //  withContext(Dispatchers.IO) {
//                repositoryViewModel.getRepositories()
//                repositoryViewModel.listRepositoriesResult().observeForever {
//                    list = it
//                   // Log.d("ListRepo", "testGetRepositoryInViewModel-> " + list.size)
//
//            //    }
//
//
//            }
//        }
//
//
//
//        assertEquals(29, list.size)
//
//        // list = repositoryViewModel.listRepositoriesResult().observe(lifeCycleTestOwner)
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