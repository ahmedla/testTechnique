package fr.bigburger.list.contrat

import fr.bigburger.list.entity.Burger

class BurgerContract {

    interface View {

        fun displayBurgerList(burgerList: List<Burger>?)

        fun showError(message: Int)

        fun showProgress(mustShow: Boolean)
    }

    interface Presenter {

        fun onFetchBurgerList()

        fun onDestroy()
    }

    interface Interactor {

        fun fetchBurgerList()
    }

    interface InteractorOutput {

        fun onSuccessFetchBurgerList(burgerList: List<Burger>?)

        fun onErrorFetchBurgerList(message: Int)
    }

    interface Repository {

        fun fetchBurgerListFromApi()
    }

    interface RepositoryOutput {

        fun onSuccessFetchBurgerLisFromApi(burgerList: List<Burger>?)


        fun onErrorFetchBurgerListFromApi(message: Int)
    }
}