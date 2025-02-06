package com.rudraksha.androidads.ads.facebook

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.facebook.ads.AdSize
import com.facebook.ads.AdView

@Composable
fun FacebookBannerAd(context: Context, adUnitId: String) {
    AndroidView(
        factory = { ctx ->
            AdView(ctx, adUnitId, AdSize.BANNER_HEIGHT_50).apply {
                loadAd() // ✅ This method comes from AdView class
            }
        }
    )
}
