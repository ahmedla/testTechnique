package fr.bigburger.basket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.bigburger.R
import fr.bigburger.global.Utils
import fr.bigburger.list.entity.Burger
import kotlinx.android.synthetic.main.item_burger.view.*

class BasketAdapter(private val burgerList: List<Burger>) :

        RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {

        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_basket, parent, false) as ConstraintLayout

        return BasketViewHolder(textView)
    }

    override fun getItemCount(): Int {

        return burgerList.size
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bindData(burgerList[position])
    }

    class BasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(burger: Burger) = with(itemView) {

            if (burger.title != null) {
                itemView.titleTextView.text = burger.title
            }
            itemView.priceTextView.text = Utils.handlePrice(itemView.context, burger.price)

            burger.thumbnail?.let {
                Picasso
                        .get()
                        .load(burger.thumbnail)
                        .placeholder(R.drawable.ic_logo)
                        .error(R.drawable.ic_logo)
                        .into(itemView.iconImageView)
            }
        }

    }
}
