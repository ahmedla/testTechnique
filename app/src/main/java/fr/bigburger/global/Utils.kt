package fr.bigburger.global

import android.content.Context
import fr.bigburger.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

class Utils {

    companion object {
        const val BURGER = "burger"

        fun handlePrice(context: Context, initPrice: Int): String {
            val euroPrice: Double = (initPrice * 1.0).roundToInt() / 100.0
            val price = DecimalFormat("##.##").format(euroPrice)
            return context.getString(R.string.price_label, price)
        }

    }
}