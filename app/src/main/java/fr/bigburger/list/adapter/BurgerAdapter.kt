package fr.bigburger.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.bigburger.R
import fr.bigburger.global.Utils.Companion.handlePrice
import fr.bigburger.list.entity.Burger
import kotlinx.android.synthetic.main.item_burger.view.*

class BurgerAdapter(private val burgerList: List<Burger>,
                    private val listener: (Burger) -> Unit) :

        RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurgerViewHolder {

        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_burger, parent, false) as ConstraintLayout

        return BurgerViewHolder(textView)
    }

    override fun getItemCount(): Int {

        return burgerList.size
    }

    override fun onBindViewHolder(holder: BurgerViewHolder, position: Int) {

        holder.bindData(burgerList[position], listener)
    }

    class BurgerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(burger: Burger, listener: (Burger) -> Unit) = with(itemView) {

            if (burger.title != null) {
                itemView.titleTextView.text = burger.title
            }
            itemView.priceTextView.text = handlePrice(itemView.context, burger.price)

            burger.thumbnail?.let {
                Picasso
                        .get()
                        .load(burger.thumbnail)
                        .placeholder(R.drawable.ic_logo)
                        .error(R.drawable.ic_logo)
                        .into(itemView.iconImageView)
            }

            itemView.checkBox.setOnCheckedChangeListener { _, isChecked ->
                burger.isSelected = isChecked
            }

            setOnClickListener { listener(burger) }
        }

    }

}