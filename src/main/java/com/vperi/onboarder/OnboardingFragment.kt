package com.vperi.onboarder

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_onboarding.view.*
import java.io.Serializable

class OnboardingFragment : Fragment(), Serializable {

  private var buttonClickListener: OnOnboardingButtonClickListener? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? =

      inflater.inflate(R.layout.fragment_onboarding, container, false).apply {
        val titleTextView: TextView = onboarding_fragment_title
        val bodyTextView: TextView = onboarding_fragment_body_text
        val imageView: ImageView = onboarding_fragment_image
        val button: Button = onboarding_fragment_button

        listOf(bodyTextView, imageView, button).forEach {
          it.visibility = View.GONE
        }

        with(arguments!!.getParcelable(PAGE_KEY) as Page) {

          titleTextView.apply {
            text = title
            titleTextColor?.let { setTextColor(resources.getColor(it)) }
          }

          bodyText?.let {
            bodyTextView.apply {
              visibility = View.VISIBLE
              text = it
              bodyTextColor?.let { setTextColor(resources.getColor(it)) }
            }
          }

          imageResId?.let {
            imageView.apply {
              visibility = View.VISIBLE
              setImageResource(it)
              maxImageHeight?.let { maxHeight = it }
            }
          }

          buttonText?.let {
            button.apply {
              visibility = View.VISIBLE
              text = it
              setOnClickListener { clickOnboardingButton(position!!) }
            }
          }
        }
      }

  private fun clickOnboardingButton(position: Int) {
    buttonClickListener?.onOnboardingClick(position)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    buttonClickListener = context as OnOnboardingButtonClickListener
  }

  override fun onDetach() {
    super.onDetach()
    buttonClickListener = null
  }

  companion object {
    private const val PAGE_KEY = "PAGE_KEY"

    fun newInstance(onboardingPage: Page,
        position: Int): OnboardingFragment {

      onboardingPage.position = position
      return OnboardingFragment().apply {
        arguments = Bundle().apply {
          putParcelable(PAGE_KEY, onboardingPage)
        }
      }
    }
  }
}
