package fr.bigburger.list.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.bigburger.R
import fr.bigburger.basket.ui.activity.BasketActivity
import fr.bigburger.detail.ui.activity.DetailActivity
import fr.bigburger.global.Utils
import fr.bigburger.list.adapter.BurgerAdapter
import fr.bigburger.list.contrat.BurgerContract
import fr.bigburger.list.entity.Burger
import fr.bigburger.list.global.BaseActivity
import fr.bigburger.list.presenter.BurgerPresenter
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity(), BurgerContract.View {

    private var presenter: BurgerContract.Presenter? = BurgerPresenter(this)

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var burgerSelectedList: MutableList<Burger>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter?.onFetchBurgerList()
        viewManager = LinearLayoutManager(this)
        handleAddButtonAnimation()

        burgerRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun displayBurgerList(burgerList: List<Burger>?) {

        burgerList?.let { item ->
            viewAdapter = BurgerAdapter(item) { itemClicked ->

                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(Utils.BURGER, itemClicked)
                startActivity(intent)
            }
            addButton.show()
            burgerRecyclerview.visibility = View.VISIBLE
            burgerRecyclerview.also {
                it.adapter = viewAdapter
            }

            addButton.setOnClickListener {
                handleUserChoice(burgerList)
                if (burgerSelectedList.isNotEmpty()) {
                    val intent = Intent(this, BasketActivity::class.java)
                    intent.putParcelableArrayListExtra(Utils.BURGER, ArrayList(burgerSelectedList))
                    startActivity(intent)
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(R.string.worning_alert_dialog_title)
                    builder.setMessage(R.string.error_alert_dialog)
                    builder.setPositiveButton(R.string.ok_button) { dialog, whichButton ->
                        dialog.dismiss()
                    }
                    builder.show()                }
            }
        }
    }

    override fun showProgress(mustShow: Boolean) {

        when (mustShow) {
            true -> progressBar.visibility = View.VISIBLE
            else -> progressBar.visibility = View.GONE
        }
    }

    override fun showError(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun handleAddButtonAnimation() {
        burgerRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && addButton.isShown)
                    addButton.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    addButton.show()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun handleUserChoice(burgerList: List<Burger>?) {
        burgerSelectedList = ArrayList()
        burgerList?.let {
            for (burger in burgerList) {
                if (burger.isSelected) {
                    burgerSelectedList.add(burger)
                }
            }
        }
    }
}
