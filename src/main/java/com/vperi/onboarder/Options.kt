@file:Suppress("unused")

package com.vperi.onboarder

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Options(
    val background: Background,
    val swipingEnabled: Boolean = true,
    val hideDots: Boolean = false,
    val pages: ArrayList<Page>
) : Parcelable

