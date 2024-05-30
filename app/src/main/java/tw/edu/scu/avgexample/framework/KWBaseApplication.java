package tw.edu.scu.avgexample.framework;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//import com.google.android.gms.ads.reward.RewardItem;
//import com.google.android.gms.ads.reward.RewardedVideoAd;
//import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;


import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.utility.KWLog;

public class KWBaseApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private int activityReferences = 0;
    private boolean isActivityChangingConfigurations = false;

    public AdView adView;
    //public RewardedVideoAd rewardedVideoAd;
    public RewardedAd rewardedAd;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);

        initAd();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (++activityReferences == 1 && !isActivityChangingConfigurations) {
            // KWBaseApplication enters foreground
            applicationDidBecomeActive();

        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        isActivityChangingConfigurations = activity.isChangingConfigurations();
        if (--activityReferences == 0 && !isActivityChangingConfigurations) {
            // KWBaseApplication enters background
            applicationDidEnterBackground();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


    protected void applicationDidEnterBackground() {

    }

    protected void applicationDidBecomeActive() {

    }

    private void initAd() {
        Toast.makeText(this, "initAd", Toast.LENGTH_SHORT);

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//                adView = new AdView(KWBaseApplication.this);
//                //adView.setAdSize(AdSize.BANNER);
//                adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
//
//                //TODO: Use Kawa's in release favor
//                adView.setAdUnitId(getResources().getString(R.string.ad_view_id_01));
//                adView.loadAd(new AdRequest.Builder().build());
//
//                rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(KWBaseApplication.this);
//                rewardedVideoAd.setRewardedVideoAdListener(KWBaseApplication.this);
//                loadRewardedVideoAd();
//            }
//        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {

            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                adView = new AdView(KWBaseApplication.this);
                adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
                adView.setAdUnitId(getResources().getString(R.string.ad_view_id_01));
                adView.loadAd(new AdRequest.Builder().build());
            }
        });

        loadRewardedAd();

    }

    public void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, getResources().getString(R.string.rewarded_video_ad_01), adRequest, new RewardedAdLoadCallback() {

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                // Handle the error.
                KWLog.d(loadAdError.toString());
                rewardedAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                super.onAdLoaded(rewardedAd);
                KWBaseApplication.this.rewardedAd = rewardedAd;
                KWLog.d("Ad was loaded.");
            }
        });
    }

    public void showRewardedAd(Activity activityContext) {
        if (rewardedAd != null) {
            rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    loadRewardedAd();
                    Toast.makeText(getApplicationContext(), "獎勵廣告播放完畢", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            loadRewardedAd();
            Toast.makeText(getApplicationContext(), "獎勵廣告尚未準備就緒，請稍後再試一次", Toast.LENGTH_LONG).show();
        }
    }

//    public void loadRewardedVideoAd() {
//        //TODO: Use Kawa's in release favor
//        rewardedVideoAd.loadAd(getResources().getString(R.string.rewarded_video_ad_01), new AdRequest.Builder().build());
//    }
//
//    @Override
//    public void onRewardedVideoAdLoaded() {
//    }
//
//    @Override
//    public void onRewardedVideoAdOpened() {
//    }
//
//    @Override
//    public void onRewardedVideoStarted() {
//        loadRewardedVideoAd();
//        Toast.makeText(getApplicationContext(), "獎勵廣告開始播放", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onRewardedVideoAdClosed() {
//        Toast.makeText(getApplicationContext(), "獎勵廣告視窗關閉", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onRewarded(RewardItem rewardItem) {
//    }
//
//    @Override
//    public void onRewardedVideoAdLeftApplication() {
//    }
//
//    @Override
//    public void onRewardedVideoAdFailedToLoad(int i) {
//        Toast.makeText(getApplicationContext(), "沒有廣告可以播放了Q_Q", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onRewardedVideoCompleted() {
//        Toast.makeText(getApplicationContext(), "獎勵廣告播放完畢", Toast.LENGTH_LONG).show();
//    }
}