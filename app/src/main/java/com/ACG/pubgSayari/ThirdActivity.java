package com.ACG.pubgSayari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class ThirdActivity extends AppCompatActivity {

    String title;
    String[] str;
    ListView second_list;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);

        second_list = findViewById(R.id.second_list);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        loadIn();
        title = getIntent().getStringExtra("title");


        if (title.equals("Pubg Shayari")) {
            str = getResources().getStringArray(R.array.pubgSayari);
        }
        if (title.equals("Pubg English Shayari")){
            str = getResources().getStringArray(R.array.PubgEnglishShayari);
        }
        if (title.equals("Pubg Game Shayari")) {
            str = getResources().getStringArray(R.array.PubgGameShayari);
        }

        if (title.equals("Pubg Shayari For Gf")) {
            str = getResources().getStringArray(R.array.PubgShayariForGf);
        }
        if (title.equals("Pubg Attitude Shayari")){
            str = getResources().getStringArray(R.array.PubgAttitudeShayari);
        }
        if (title.equals("Pubg Status")){
            str = getResources().getStringArray(R.array.PubgStatus);
        }
        if (title.equals("Pubg Attitude Shayari 2")){
            str = getResources().getStringArray(R.array.PubgAttitudeShayari2);
        }


        SecondAdapter add = new SecondAdapter(ThirdActivity.this, str);
        second_list.setAdapter(add);
        second_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent last = new Intent(ThirdActivity.this, ShayriLastActivity.class);
                last.putExtra("shayri", str);
                last.putExtra("position", i);
                startActivity(last);

            }
        });

    }

    public void loadIn() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,  getResources().getString(R.string.in_ad_unit), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.

                mInterstitialAd = interstitialAd;
                Log.i("TAG", "onAdLoaded");

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(ThirdActivity.this);

                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i("TAG", loadAdError.getMessage());
                mInterstitialAd = null;

            }
        });

    }




}
