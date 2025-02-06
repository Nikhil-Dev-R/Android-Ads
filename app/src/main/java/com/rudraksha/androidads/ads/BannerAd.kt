package com.rudraksha.androidads.ads

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.LoadAdError

@Composable
fun BannerAdView(adUnitId: String) {
    AndroidView(
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = adUnitId
                loadAd(AdRequest.Builder().build())

                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        println("Banner Ad Loaded")
                    }

                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        println("Banner Ad Failed: ${adError.message}")
                    }
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
