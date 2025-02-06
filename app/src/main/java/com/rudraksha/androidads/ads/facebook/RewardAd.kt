package com.rudraksha.androidads.ads.facebook

import android.content.Context
import com.facebook.ads.*

class FacebookRewardedAd(private val context: Context, private val adUnitId: String) {
    private var rewardedVideoAd: RewardedVideoAd? = null

    fun loadAd() {
        rewardedVideoAd = RewardedVideoAd(context, adUnitId).apply {
            loadAd(buildLoadAdConfig().withAdListener(object : RewardedVideoAdListener {
                override fun onRewardedVideoCompleted() {}
                override fun onRewardedVideoClosed() { loadAd() } // ✅ Reload after watching
                override fun onError(ad: Ad?, error: AdError?) {}
                override fun onAdLoaded(ad: Ad?) {}
                override fun onAdClicked(ad: Ad?) {}
                override fun onLoggingImpression(ad: Ad?) {}
            }).build())
        }
    }

    fun showAd() {
        rewardedVideoAd?.let {
            if (it.isAdLoaded) it.show()
        }
    }
}
