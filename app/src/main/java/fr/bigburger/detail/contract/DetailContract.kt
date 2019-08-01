package fr.bigburger.detail.contract

import android.content.Intent
import fr.bigburger.list.entity.Burger

class DetailContract {

    interface View {

        fun displayDetailBurger(burger: Burger?)
    }

    interface Presenter {

        fun onFetchBurger(intent: Intent?)

        fun onDestroy()
    }
}