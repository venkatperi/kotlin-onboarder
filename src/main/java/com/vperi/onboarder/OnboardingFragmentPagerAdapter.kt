package com.vperi.onboarder

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class OnboardingFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    private val pages: ArrayList<Page>?) :
    FragmentPagerAdapter(fragmentManager) {

  override fun getItem(position: Int) =
      OnboardingFragment.newInstance(pages!![position], position)

  override fun getCount() = pages!!.size
}