package fr.bigburger.detail.presenter

import android.content.Intent
import fr.bigburger.detail.contract.DetailContract
import fr.bigburger.global.Utils
import fr.bigburger.list.entity.Burger

class DetailPresenter(private var view: DetailContract.View?) : DetailContract.Presenter {

    override fun onFetchBurger(intent: Intent?) {

        if (intent != null && intent.extras != null) {
            val burger: Burger = intent.getParcelableExtra(Utils.BURGER)
            view?.displayDetailBurger(burger)
        }
    }

    override fun onDestroy() {
        view = null
    }
}