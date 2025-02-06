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
import com.rudraksha.androidads.ads.facebook.FacebookBannerAd
import com.rudraksha.androidads.ads.facebook.FacebookInterstitialAd
import com.rudraksha.androidads.ads.facebook.FacebookRewardedAd
import com.rudraksha.androidads.ads.google.AdMobInterstitial
import com.rudraksha.androidads.ads.google.AdMobRewarded
import com.rudraksha.androidads.ads.google.BannerAdView

@Composable
fun MyScreen(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        // Google Ads
//        val defaultAdUnitIdBanner = "ca-app-pub-3940256099942544/6300978111" // For testing
//
//        Text("Ad Interstitial")
//        InterstitialAdButton(context)
//
//        Text("Ad Rewards")
//        RewardedAdButton(context)
//
//        Text("Ad Banner")
//        Spacer(modifier = Modifier.weight(1f))  // Push ad to bottom
//        BannerAdView(adUnitId = defaultAdUnitIdBanner) // Test Ad ID

        // Facebook Ads
//        Text("Ad Interstitial")
//        InterstitialAdButtonFacebook(context)
//
//        Text("Ad Rewards")
//        RewardedAdButtonFacebook(context)
//
//        Spacer(modifier = Modifier.weight(1f)) // Push ad to bottom
//        FacebookBannerAd(context, "YOUR_PLACEMENT_ID")
    }
}

@Composable
fun InterstitialAdButton(context: Context) {
    val defaultAdUnitIdInterstitial = "ca-app-pub-3940256099942544/1033173712" // For testing
    val adManager = remember { AdMobInterstitial(context, defaultAdUnitIdInterstitial) }
    LaunchedEffect(Unit) { adManager.loadAd() }

    Button(onClick = { adManager.showAd() }) {
        Text("Show Interstitial Ad")
    }
}

@Composable
fun RewardedAdButton(context: Context) {
    val defaultAdUnitIdRewarded = "ca-app-pub-3940256099942544/5224354917" // For testing
    val adManager = remember { AdMobRewarded(context, defaultAdUnitIdRewarded) }
    LaunchedEffect(Unit) { adManager.loadAd() }

    Button(onClick = { adManager.showAd { reward ->
        println("User got ${reward.amount} ${reward.type}")
    } }) {
        Text("Show Rewarded Ad")
    }
}

@Composable
fun InterstitialAdButtonFacebook(context: Context) {
    val adManager = remember { FacebookInterstitialAd(context, "YOUR_PLACEMENT_ID") }

    LaunchedEffect(Unit) { adManager.loadAd() }

    Button(onClick = { adManager.showAd() }) {
        Text("Show Interstitial Ad")
    }
}

@Composable
fun RewardedAdButtonFacebook(context: Context) {
    val adManager = remember { FacebookRewardedAd(context, "YOUR_PLACEMENT_ID") }

    LaunchedEffect(Unit) { adManager.loadAd() }

    Button(onClick = { adManager.showAd() }) {
        Text("Show Rewarded Ad")
    }
}

