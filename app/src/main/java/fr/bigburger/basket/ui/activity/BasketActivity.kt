package fr.bigburger.basket.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.bigburger.R
import fr.bigburger.basket.adapter.BasketAdapter
import fr.bigburger.basket.contract.BasketContract
import fr.bigburger.basket.presenter.BasketPresenter
import fr.bigburger.global.Utils
import fr.bigburger.list.entity.Burger
import fr.bigburger.list.global.BaseActivity
import kotlinx.android.synthetic.main.activity_basket.*

class BasketActivity : BaseActivity(), BasketContract.View {

    private var presenter: BasketContract.Presenter? = BasketPresenter(this)

    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    private var totalPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        presenter?.onFetchBurgerList(intent)
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun displayBasketItems(basketList: List<Burger>?) {
        basketList?.let { item ->
            viewAdapter = BasketAdapter(item)
            basketRecyclerview.also {
                it.adapter = viewAdapter
            }

            totalPriceTextView.text = getString(R.string.total_label,
                    Utils.handlePrice(this, handlePriceToPay(basketList)))

            payButton.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.alert_dialog_title)
                builder.setMessage(R.string.alert_dialog_description)
                builder.setPositiveButton(R.string.ok_button) { dialog, whichButton ->
                    dialog.dismiss()
                }
                builder.show()
            }
        }
    }

    private fun handlePriceToPay(burgerList: List<Burger>?): Int {
        burgerList?.let {
            for (burger in burgerList) {
                totalPrice += burger.price
            }
        }
        return totalPrice
    }
}
