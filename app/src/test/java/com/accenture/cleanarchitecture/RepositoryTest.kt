package com.accenture.cleanarchitecture

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.accenture.cleanarchitecture.app.di.SubComponent
import com.accenture.cleanarchitecture.data.repository.RepoRepositoryImpl
import com.accenture.cleanarchitecture.app.features.repository.viewmodel.RepositoryViewModel
import com.accenture.cleanarchitecture.data.api.endpoints.RepositoryEndPoint
import com.accenture.cleanarchitecture.data.enuns.Status
import com.accenture.cleanarchitecture.domain.repo.RepoRepository
import com.accenture.cleanarchitecture.domain.usecases.GetRepositories
import com.accenture.cleanarchitecture.domain.usecases.VerifyNextPageGetRepository
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    lateinit var repoRepositoryImpl: RepoRepositoryImpl

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

    lateinit var subComponent: SubComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var verifyNextPageMockk: VerifyNextPageGetRepository


    lateinit var getRepositoriesMockk: GetRepositories


    private lateinit var  repositoryViewModel: RepositoryViewModel

    private lateinit var context: Context

    private lateinit var repositoryRepo: RepoRepository




    @Before
    open fun setUp() {
//        lifeCycleTestOwner = LifeCycleTestOwner()
//        lifeCycleTestOwner.onCreate()
//        repoRepositoryImpl = Mockito.mock(RepoRepositoryImpl::class.java)
//        repositoryViewModel = RepositoryViewModel()
//        Dispatchers.setMain(dispatcher)

//            val component = DaggerTestAppComponent.builder()
//                .applicationModule(TestApplicationModule(GDelegate()))
//                .build()
//            component.into(this)
//
//        context = Mockito.mock(Context::class.java)
//
//        repositoryViewModel =  ViewModelProviders.of(context, viewModelFactory)[RepositoryViewModel::class.java]

        //FakeAndroidKeyStore.setup
        //getRepositoriesMockk = mock(GetRepositories::class.java)

         var endPoint: RepositoryEndPoint = mock(RepositoryEndPoint::class.java)

        repositoryRepo = RepoRepositoryImpl(endPoint)

        getRepositoriesMockk = GetRepositories(repositoryRepo)
        verifyNextPageMockk = VerifyNextPageGetRepository()
        repositoryViewModel = mock(RepositoryViewModel::class.java)
//        repositoryViewModel = RepositoryViewModel().apply {
//            getRepositories = getRepositoriesMockk
//            verifyNextPage = verifyNextPageMockk
//        }

        //subComponent = (contextapp as MyApplication).appComponent.uiComponent().create()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getRepositoryTest() {
        runBlocking {
          val result =  getRepositoriesMockk.execute(2)

            if (result.status == Status.SUCCESS){
                System.out.println("Sucess: " + result + ": " + result.data!![0].name);
                assertTrue(result.data!!.size > 0)
            }
        }
    }


    @Test
    fun getRepositoryTestViewModel() {
        runBlocking {
            repositoryViewModel.getRepositories()
//                delay(50000)
//                repositoryViewModel.listRepositoriesResult().observeForever {list->
//                    assertTrue(list.isNotEmpty())
//
//                }



        }
    }

    @Test
    fun verifiyTest() {
        runBlocking {
            repositoryViewModel.getMoreItems(10,10,20)
            Mockito.verify(repositoryViewModel, Mockito.times(1)).getRepositories()

//            if (result.status == Status.SUCCESS){
//                System.out.println("Sucess: " + result + ": " + result.data!![0].name);
//                assertTrue(result.data!!.size > 0)
            }
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


//    private fun mock(): List<RepositoryDTO> {
//        var list = ArrayList<RepositoryDTO>()
//        list.add(
//            RepositoryDTO(
//                "Anderson",
//                "Anderson Carlos",
//                "Dev Android",
//                10,
//                10,
//                AuthorDTO("anderson", "")
//            )
//        )
//
//        list.add(
//            RepositoryDTO(
//                "Anderson2",
//                "Anderson Carlos2",
//                "Dev Android2",
//                20,
//                20,
//                AuthorDTO("anderson2", "")
//            )
//        )
//
//        return list
//
//    }

}