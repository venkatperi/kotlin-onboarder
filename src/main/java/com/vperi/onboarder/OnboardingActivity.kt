package com.vperi.onboarder

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_onboarding.*

open class OnboardingActivity : AppCompatActivity(),
    OnOnboardingButtonClickListener {

  private var activityResult = RESULT_OK

  private var pages: ArrayList<Page>? = null

  private val pagerAdapter by lazy {
    OnboardingFragmentPagerAdapter(supportFragmentManager, pages)
  }

  private val viewPager by lazy { onboarding_viewpager }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_onboarding)
    supportActionBar!!.hide()

    val options: Options = intent.getParcelableExtra(OPTIONS_KEY)
    pages = options.pages

    val circlePageIndicator = onboadring_page_indicator
    circlePageIndicator.visibility = View.GONE

    //Set the view pager
    if (options.swipingEnabled) {
      viewPager!!.adapter = pagerAdapter

      if (!options.hideDots) {
        circlePageIndicator.visibility = View.VISIBLE
        circlePageIndicator.setViewPager(viewPager)
      }
    } else {
      //Non-swiping version
      viewPager!!.visibility = View.GONE

      supportFragmentManager
          .beginTransaction()
          .apply {
            add(R.id.onboarding_layout, pagerAdapter.getItem(0))
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
          }.commit()
    }

    //Set the background Image or Color
    val background = onboarding_background_image
    when (options.background) {
      is Background.Image -> {
        background.setImageResource(
            options.background.resourceId)
      }

      is Background.Color ->
        window.decorView.setBackgroundColor(
            resources.getColor(
                options.background.resourceId))
    }
  }

  /** Convenience method invoked by the user to make to the next page in the list (if there are any left)  */
  private fun goToNextFragment(currentPosition: Int) =
      when {
        currentPosition + 1 >= pagerAdapter.count -> {
          setResult(activityResult)
          finish()
        }
        else -> viewPager!!.currentItem = currentPosition + 1
      }

  override fun onOnboardingClick(position: Int) {
    goToNextFragment(position)
  }

  companion object {
    @Suppress("unused")
    private val TAG = OnboardingActivity::class.java.simpleName

    const val OPTIONS_KEY = "OPTIONS"
  }
}
