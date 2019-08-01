package fr.bigburger.basket.contract

import android.content.Intent
import fr.bigburger.list.entity.Burger

class BasketContract {

    interface View {

        fun displayBasketItems(basketList: List<Burger>?)
    }

    interface Presenter {

        fun onFetchBurgerList(intent: Intent?)

        fun onDestroy()
    }
}