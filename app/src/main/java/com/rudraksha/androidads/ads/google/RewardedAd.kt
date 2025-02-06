package com.rudraksha.androidads.ads.google

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.*

class AdMobRewarded(private val context: Context, private val adUnitId: String) {
    private var rewardedAd: RewardedAd? = null

    fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(context, adUnitId, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdLoaded(ad: RewardedAd) {
                rewardedAd = ad
                println("Rewarded Ad Loaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                println("Rewarded Ad Failed to Load: ${adError.message}")
            }
        })
    }

    fun showAd(onRewardEarned: (RewardItem) -> Unit) {
        rewardedAd?.let { ad ->
            if (context is Activity) {
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        println("Rewarded Ad Closed")
                        loadAd() // Reload ad after dismissal
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        println("Rewarded Ad Failed to Show: ${adError.message}")
                    }

                    override fun onAdShowedFullScreenContent() {
                        println("Rewarded Ad is Showing")
                        rewardedAd = null // Prevent reuse
                    }
                }
                ad.show(context) { rewardItem ->
                    println("User Earned: ${rewardItem.amount} ${rewardItem.type}")
                    onRewardEarned(rewardItem)
                }
            }
        } ?: println("Rewarded Ad is not ready yet!")
    }
}
