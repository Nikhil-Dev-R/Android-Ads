package com.rudraksha.androidads

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rudraksha.androidads.ads.AdMobInterstitial
import com.rudraksha.androidads.ads.AdMobRewarded
import com.rudraksha.androidads.ads.BannerAdView

@Composable
fun MyScreen(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val adUnitIdBanner = "ca-app-pub-2207843103312120/3103650416" // From account
        val defaultAdUnitIdBanner = "ca-app-pub-3940256099942544/6300978111"

        Text("Ad Interstitial")
        InterstitialAdButton(LocalContext.current)

        Text("Ad Rewards")
        RewardedAdButton(LocalContext.current)

        Text("Ad Banner")
        Spacer(modifier = Modifier.weight(1f))  // Push ad to bottom
        BannerAdView(adUnitId = defaultAdUnitIdBanner) // Test Ad ID
    }
}

@Composable
fun InterstitialAdButton(context: Context) {
    val adUnitIdInterstitial = "ca-app-pub-2207843103312120/3103650416"// From account
    val defaultAdUnitIdInterstitial = "ca-app-pub-3940256099942544/1033173712"
    val adManager = remember { AdMobInterstitial(context, defaultAdUnitIdInterstitial) }
    LaunchedEffect(Unit) { adManager.loadAd() }

    Button(onClick = { adManager.showAd() }) {
        Text("Show Interstitial Ad")
    }
}

@Composable
fun RewardedAdButton(context: Context) {
    val adUnitIdRewarded = "ca-app-pub-2207843103312120/3319837487" // From account
    val defaultAdUnitIdRewarded = "ca-app-pub-3940256099942544/5224354917"
    val adManager = remember { AdMobRewarded(context, defaultAdUnitIdRewarded) }
    LaunchedEffect(Unit) { adManager.loadAd() }

    Button(onClick = { adManager.showAd { reward ->
        println("User got ${reward.amount} ${reward.type}")
    } }) {
        Text("Show Rewarded Ad")
    }
}


