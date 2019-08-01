package fr.bigburger.detail.presenter

import android.content.Intent
import android.os.Bundle
import fr.bigburger.detail.contract.DetailContract
import fr.bigburger.list.entity.Burger
import org.junit.Before
import org.junit.Test
import org.mockito.*

class DetailPresenterTest {


    @Mock
    lateinit var view: DetailContract.View

    @Mock
    lateinit var intent: Intent

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onFetchBurgerListTest(){
        var burger =Burger("test", "test" ,"test" ,"test" , 0, false)
        Mockito.`when`(intent.getParcelableExtra<Burger>(ArgumentMatchers.anyString())).thenReturn(burger)
        Mockito.`when`(intent.extras).thenReturn(Bundle())
        var detailPresenter = DetailPresenter(view)
        detailPresenter.onFetchBurger(intent)
        detailPresenter.onDestroy()
        detailPresenter.onFetchBurger(intent)
        Mockito.verify(view, Mockito.times(1)).displayDetailBurger(burger)
    }
}