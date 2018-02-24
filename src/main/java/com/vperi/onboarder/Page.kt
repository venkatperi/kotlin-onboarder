package com.vperi.onboarder

import android.annotation.SuppressLint
import android.os.Parcelable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class Page(val title: String,
    var position: Int? = null,
    @ColorRes val titleTextColor: Int? = null,
    val bodyText: String? = null,
    @ColorRes val bodyTextColor: Int? = null,
    @DrawableRes val imageResId: Int? = null,
    val maxImageHeight: Int? = null,
    val buttonText: String? = null,
    val id: String) : Parcelable

