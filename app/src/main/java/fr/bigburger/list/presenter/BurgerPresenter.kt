package fr.bigburger.list.presenter

import fr.bigburger.list.contrat.BurgerContract
import fr.bigburger.list.entity.Burger
import fr.bigburger.list.interactor.BurgerInteractor

class BurgerPresenter(private var view: BurgerContract.View?) : BurgerContract.Presenter, BurgerContract.InteractorOutput {

    private var interactor: BurgerContract.Interactor? = BurgerInteractor(this)

    override fun onFetchBurgerList() {

        view?.showProgress(true)

        interactor?.fetchBurgerList()
    }

    override fun onSuccessFetchBurgerList(burgerList: List<Burger>?) {

        view?.showProgress(false)

        view?.displayBurgerList(burgerList)
    }

    override fun onErrorFetchBurgerList(message: Int) {

        view?.showProgress(false)

        view?.showError(message)
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }
}