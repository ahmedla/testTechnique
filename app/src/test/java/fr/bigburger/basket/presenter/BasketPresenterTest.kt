package fr.bigburger.basket.presenter

import android.content.Intent
import fr.bigburger.basket.contract.BasketContract
import fr.bigburger.list.entity.Burger
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BasketPresenterTest {

    @Mock
    lateinit var intent: Intent

    @Mock
    lateinit var view: BasketContract.View

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onFetchBurgerListTest(){
        var burger =Burger("test", "test" ,"test" ,"test" , 0, true)
        var list = ArrayList<Burger>()
        list.add(burger)
        list.add(burger)
        Mockito.`when`(intent.getParcelableArrayListExtra<Burger>(ArgumentMatchers.anyString())).thenReturn(list)
        BasketPresenter(view).onDestroy()
        BasketPresenter(view).onFetchBurgerList(intent)
        Mockito.verify(view, Mockito.times(1)).displayBasketItems(list)

    }
}