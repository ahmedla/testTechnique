package fr.bigburger.list.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Burger(var ref: String?,
                  var title: String?,
                  var description: String?,
                  var thumbnail: String?,
                  var price: Int,
                  var isSelected: Boolean): Parcelable