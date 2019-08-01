package fr.bigburger.basket.presenter

import android.content.Intent
import fr.bigburger.basket.contract.BasketContract
import fr.bigburger.global.Utils
import fr.bigburger.list.entity.Burger

class BasketPresenter(private var view: BasketContract.View?) : BasketContract.Presenter {

    override fun onFetchBurgerList(intent: Intent?) {

        if (intent != null) {
            val burgerSelectedList: List<Burger> = intent.getParcelableArrayListExtra(Utils.BURGER)
            view?.displayBasketItems(burgerSelectedList)
        }
    }

    override fun onDestroy() {
        view = null
    }
}