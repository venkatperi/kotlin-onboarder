package com.vperi.onboarder

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import java.io.Serializable

sealed class Background(val resourceId: Int) : Serializable {
  class Image(@DrawableRes
  resourceId: Int) : Background(resourceId)

  class Color(@ColorRes
  resourceId: Int) : Background(resourceId)
}