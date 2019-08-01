package fr.bigburger.list.presenter

import fr.bigburger.list.contrat.BurgerContract
import fr.bigburger.list.entity.Burger
import org.junit.Before
import org.junit.Test
import org.mockito.*

class BurgerPresenterTest {

    @Mock
        lateinit var view: BurgerContract.View

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onFetchBurgerListTest(){

        var burgerPresenter = BurgerPresenter(view)
        burgerPresenter.onErrorFetchBurgerList(0)
        burgerPresenter.onSuccessFetchBurgerList(ArrayList<Burger>())
        burgerPresenter.onDestroy()
        Mockito.verify(view, Mockito.times(1)).displayBurgerList(ArgumentMatchers.any())
        Mockito.verify(view, Mockito.times(2)).showProgress(false)
        Mockito.verify(view, Mockito.times(1)).showError(ArgumentMatchers.anyInt())
    }
}