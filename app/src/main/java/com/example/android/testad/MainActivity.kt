package com.example.android.testad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var ivMain: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)

        ivMain = findViewById(R.id.iv_main)

        loadAd()
    }

    private fun loadAd() {

        AdLoader.Builder(this, "ad_unit_id")
            .forCustomFormatAd("template_id", {
                ivMain.setImageDrawable(it.getImage("urlhdef")?.drawable)
                ivMain.setOnClickListener { _ ->
                    it.performClick("urlhdef")
                }
            }, { _, name ->
                Log.v("Ads", "Did click on $name")
            }).build().loadAd(getRequest())

    }

    private fun getRequest(): AdManagerAdRequest {

        val request = AdManagerAdRequest.Builder()
        request.addCustomTargeting("has_advertising", "1")
        request.addCustomTargeting("age_range", "all")

        return request.build()
    }

}