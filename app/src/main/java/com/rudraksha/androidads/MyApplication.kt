package com.rudraksha.androidads

import android.app.Application
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Google Ads
        MobileAds.initialize(this) { }

        // Facebook Ads
        AudienceNetworkAds.initialize(this)
    }
}
