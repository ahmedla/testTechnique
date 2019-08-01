package fr.bigburger.list.interactor

import fr.bigburger.list.contrat.BurgerContract
import fr.bigburger.list.entity.Burger
import fr.bigburger.list.repository.BurgerRepository

class BurgerInteractor(private var output: BurgerContract.InteractorOutput) : BurgerContract.Interactor, BurgerContract.RepositoryOutput {

    private val repository: BurgerContract.Repository = BurgerRepository(this)

    override fun onSuccessFetchBurgerLisFromApi(burgerList: List<Burger>?) {
        output.onSuccessFetchBurgerList(burgerList)
    }

    override fun onErrorFetchBurgerListFromApi(message: Int) {
        output.onErrorFetchBurgerList(message)
    }

    override fun fetchBurgerList() {
        // Logic business
        repository.fetchBurgerListFromApi()
    }
}