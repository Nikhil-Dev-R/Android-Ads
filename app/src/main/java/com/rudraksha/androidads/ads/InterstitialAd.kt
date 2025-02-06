package com.rudraksha.androidads.ads

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdMobInterstitial(private val context: Context, private val adUnitId: String) {
    private var interstitialAd: InterstitialAd? = null

    fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                interstitialAd = ad
                println("Interstitial Ad Loaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                println("Interstitial Ad Failed: ${adError.message}")
            }
        })
    }

    fun showAd() {
        interstitialAd?.let { ad ->
            if (context is Activity) {
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        println("Interstitial Ad Closed")
                        loadAd() // Reload ad after dismissal
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        println("Interstitial Ad Failed to Show: ${adError.message}")
                    }

                    override fun onAdShowedFullScreenContent() {
                        println("Interstitial Ad is Showing")
                        interstitialAd = null // Prevent reuse
                    }
                }
                ad.show(context)
            }
        } ?: println("Interstitial Ad is not ready yet!")
    }
}
