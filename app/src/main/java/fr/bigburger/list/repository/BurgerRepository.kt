package fr.bigburger.list.repository

import fr.bigburger.R
import fr.bigburger.list.api.RestClient
import fr.bigburger.list.contrat.BurgerContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BurgerRepository(private var output: BurgerContract.RepositoryOutput) : BurgerContract.Repository {

    override fun fetchBurgerListFromApi() {

        val service = RestClient.create()
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getBurgersAsync()
            try {
                val response = request.await()
                if (response.isSuccessful) {
                    output.onSuccessFetchBurgerLisFromApi(response.body())
                } else {
                    output.onErrorFetchBurgerListFromApi(R.string.error_occurred_burgers)
                }
            } catch (e: HttpException) {
                output.onErrorFetchBurgerListFromApi(R.string.error_no_internet_connexion)

            } catch (e: Throwable) {
                output.onErrorFetchBurgerListFromApi(R.string.error_occurred_burgers)
            }
        }
    }
}
