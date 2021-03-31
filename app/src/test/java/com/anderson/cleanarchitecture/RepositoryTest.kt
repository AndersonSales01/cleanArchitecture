package com.anderson.cleanarchitecture

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.anderson.cleanarchitecture.app.di.SubComponent
import com.anderson.cleanarchitecture.data.repository.RepoRepositoryImpl
import com.anderson.cleanarchitecture.app.features.repository.viewmodel.RepositoryViewModel
import com.anderson.cleanarchitecture.data.api.endpoints.RepositoryEndPoint
import com.anderson.cleanarchitecture.data.enuns.Status
import com.anderson.cleanarchitecture.domain.entities.Author
import com.anderson.cleanarchitecture.domain.entities.Repository
import com.anderson.cleanarchitecture.domain.repo.RepoRepository
import com.anderson.cleanarchitecture.domain.usecases.GetRepositories
import com.anderson.cleanarchitecture.domain.usecases.VerifyNextPageGetRepository
import com.anderson.cleanarchitecture.util.SharedPreferenceUtil
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.Verify
import org.amshove.kluent.called
import org.amshove.kluent.on
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class RepositoryTest {

    lateinit var repoRepositoryImpl: RepoRepositoryImpl

//    @get:Rule
//    val coroutineTestRule = CoroutineTestRule()
//    @get:Rule
//    var rule: TestRule = InstantTaskExecutorRule()
//    @get:Rule
//    val coroutineTestRule = CoroutineTestRule()
//
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

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

    @Mock
    lateinit var getRepositoriesMockk: GetRepositories


    private lateinit var  repositoryViewModel: RepositoryViewModel
    @Mock
    private lateinit var sharedPref: SharedPreferenceUtil

    private lateinit var context: Context

    private lateinit var repositoryRepo: RepoRepository

    @Mock
    lateinit var listObserver: Observer<List<Repository>>
    @Mock
    lateinit var loadObserver: Observer<Boolean>

    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner




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

        MockitoAnnotations.initMocks(this)

         var endPoint: RepositoryEndPoint = mock(RepositoryEndPoint::class.java)

        repositoryRepo = RepoRepositoryImpl(endPoint)

       // getRepositoriesMockk = GetRepositories(repositoryRepo)
        verifyNextPageMockk = VerifyNextPageGetRepository()
        //repositoryViewModel = mock(RepositoryViewModel::class.java)

        repositoryViewModel = RepositoryViewModel(sharedPref)
//        repositoryViewModel = RepositoryViewModel().apply {
//            getRepositories = getRepositoriesMockk
//            verifyNextPage = verifyNextPageMockk
//        }

        //subComponent = (contextapp as MyApplication).appComponent.uiComponent().create()

        lifeCycleTestOwner = LifeCycleTestOwner()
        lifeCycleTestOwner.onCreate()

//        repositoryViewModel.listRepositoriesResult().observe(lifeCycleTestOwner, listObserver)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        lifeCycleTestOwner.onDestroy()
    }

    @Test
    fun getRepositoryTest() {
        testCoroutineRule.runBlockingTest {
          val result =  getRepositoriesMockk.execute(2)

            print(result)

//            if (result.status == Status.SUCCESS){
//                System.out.println("Sucess: " + result + ": " + result.data!![0].name);
//                assertTrue(result.data!!.size > 0)
//            }
        }
    }


    @Test
    fun getRepositoryTestViewModel() {
//        runBlocking {
//
//            repositoryViewModel.getRepositories()
//                delay(50000)
//                repositoryViewModel.listRepositoriesResult().observeForever {list->
//                    assertTrue(list.isNotEmpty())
//
//                }



//          val test=   repositoryViewModel.listRepositoriesResult().value
          //  assertTrue(test.size > 0 )


     //   }

//        coroutineTestRule.testDispatcher.runBlockingTest {
//            // Given
//            lifeCycleTestOwner.onResume()
//
//            // Then
//            Verify on listObserver that listObserver.onChanged(Lis) was called
//        }
        testCoroutineRule.runBlockingTest {
            repositoryViewModel.getRepositories()
            delay(50000)
       //    val test = repositoryViewModel.listRepositoriesResult().observeForever(listObserver)
          //  val test = repositoryViewModel.loading().observeForever(loadObserver)

//            repositoryViewModel.loading().observeForever {
//                print(it)
//                assertTrue(it)
//            }

//            verify(listObserver).onChanged(mock())

            repositoryViewModel.listRepositoriesResult().observeForever {
                print(it)
                assertTrue(it.isNotEmpty())
            }


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


    private fun mock(): List<Repository> {
        var list = ArrayList<Repository>()
        list.add(
            Repository(
                "Anderson",
                "Anderson Carlos",
                "Dev Android",
                10,
                10,
                Author("anderson", "")
            )
        )

        list.add(
            Repository(
                "Anderson2",
                "Anderson Carlos2",
                "Dev Android2",
                20,
                20,
                Author("anderson2", "")
            )
        )

        return list

    }

}