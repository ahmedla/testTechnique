package fr.bigburger.detail.ui.activity

import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.bigburger.R
import fr.bigburger.detail.contract.DetailContract
import fr.bigburger.detail.presenter.DetailPresenter
import fr.bigburger.global.Utils
import fr.bigburger.list.entity.Burger
import fr.bigburger.list.global.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), DetailContract.View {

    private var presenter: DetailContract.Presenter? = DetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter?.onFetchBurger(intent)
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun displayDetailBurger(burger: Burger?) {

        burger?.let {
            titleTextView.text = it.title
            descriptionTextView.text = it.description
            priceTextView.text = Utils.handlePrice(this, it.price)
            it.thumbnail?.let { th ->
                Picasso
                        .get()
                        .load(th)
                        .placeholder(R.drawable.ic_logo)
                        .error(R.drawable.ic_logo)
                        .into(iconImageView)
            }
        }

    }

}
