package com.rudraksha.androidads.ads.facebook

import android.app.Activity
import android.content.Context
import com.facebook.ads.*

class FacebookInterstitialAd(private val context: Context, private val adUnitId: String) {
    private var interstitialAd: InterstitialAd? = null

    fun loadAd() {
        interstitialAd = InterstitialAd(context, adUnitId).apply {
            loadAd(
                buildLoadAdConfig()
                    .withAdListener(object : InterstitialAdListener {
                        override fun onInterstitialDisplayed(ad: Ad) {}
                        override fun onInterstitialDismissed(ad: Ad) {
                            loadAd() // ✅ Reload the ad after it's closed
                        }
                        override fun onError(ad: Ad?, error: AdError?) {}
                        override fun onAdLoaded(ad: Ad?) {}
                        override fun onAdClicked(ad: Ad?) {}
                        override fun onLoggingImpression(ad: Ad?) {}
                    }).build()
            )
        }
    }

    fun showAd() {
        (context as? Activity)?.let {
            if (interstitialAd?.isAdLoaded == true) {
                interstitialAd?.show()
            }
        }
    }
}
