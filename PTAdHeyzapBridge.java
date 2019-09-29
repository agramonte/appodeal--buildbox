package com.secrethq.ads;

import java.lang.ref.WeakReference;

import org.cocos2dx.lib.Cocos2dxActivity;

import com.appodeal.ads.*;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class PTAdHeyzapBridge {
	private static native String bannerId();
	private static native String interstitialId();
	private static native void interstitialDidFail();
	private static native void rewardVideoComplete();

	private static final String TAG = "AppodealBridge";

	/// Please modify these items.
	private static final int bannerPosition = Appodeal.BANNER_BOTTOM;
	/// end


	private static Cocos2dxActivity activity;
	private static WeakReference<Cocos2dxActivity> s_activity;

	public static void initBridge(Cocos2dxActivity activity){
		Log.v(TAG, "PTAdHeyzapBridge -- INIT");
		PTAdHeyzapBridge.s_activity = new WeakReference<Cocos2dxActivity>(activity);
		PTAdHeyzapBridge.activity = activity;

		PTAdHeyzapBridge.initBanner();
		PTAdHeyzapBridge.initVideo();
		PTAdHeyzapBridge.initInterstitial();

	}

	public static void initBanner(){

	}

	public static void initInterstitial(){
		Log.v(TAG, "AppodealBridge -- Init Interstitial");

		Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
			@Override
			public void onInterstitialLoaded(boolean isPrecache) {
				// Called when interstitial is loaded
			}
			@Override
			public void onInterstitialFailedToLoad() {
				Log.v(TAG, "Appodeal -- onInterstitialFailedToLoad");
				interstitialDidFail();
			}

			@Override
			public void onInterstitialShown() {
				// Called when interstitial is shown
			}
			@Override
			public void onInterstitialClicked() {
				// Called when interstitial is clicked
			}
			@Override
			public void onInterstitialClosed() {
				// Called when interstitial is closed
			}
			@Override
			public void onInterstitialExpired()  {
				// Called when interstitial is expired
			}
		});

	}

	public static void initVideo() {

		Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
			@Override
			public void onRewardedVideoLoaded(boolean isPrecache) {
				// Called when rewarded video is loaded
			}
			@Override
			public void onRewardedVideoFailedToLoad() {
				// Called when rewarded video failed to load
			}
			@Override
			public void onRewardedVideoShown() {
				// Called when rewarded video is shown
			}
			@Override
			public void onRewardedVideoClicked() {
				// Called when rewarded video is clicked
			}
			@Override
			public void onRewardedVideoFinished(double amount, String name) {

				rewardVideoComplete();
			}
			@Override
			public void onRewardedVideoClosed(boolean finished) {
				// Called when rewarded video is closed
			}
			@Override
			public void onRewardedVideoExpired() {
				// Called when rewarded video is expired
			}
		});
	}

	public static void showRewardedVideo(){
		Log.v(TAG, "Appodeal -- showRewardedVideo");


		PTAdHeyzapBridge.s_activity.get().runOnUiThread( new Runnable() {
			public void run() {
				if (Appodeal.isLoaded(Appodeal.REWARDED_VIDEO)) {
					Appodeal.show(PTAdHeyzapBridge.activity, Appodeal.REWARDED_VIDEO);
				}
			}
		});

	}

	public static void startSession( String sdkKey ){
		if(sdkKey != null && sdkKey != ""){

			Appodeal.initialize(PTAdHeyzapBridge.activity, sdkKey, Appodeal.INTERSTITIAL | Appodeal.BANNER | Appodeal.REWARDED_VIDEO, false);
			Log.v(TAG, "Appodeal -- Start Session: " + sdkKey);

			Log.v(TAG, "Appodeal SDK Version : " + Appodeal.getVersion());
			PTAdHeyzapBridge.initVideo();
		}else{
			Log.v(TAG, "Start Session sdk key is null or empty.");
		}
	}

	public static void showFullScreen(){
		Log.v(TAG, "Appodeal -- showFullScreen");

		PTAdHeyzapBridge.s_activity.get().runOnUiThread( new Runnable() {
			public void run() {
				Appodeal.show(PTAdHeyzapBridge.activity, Appodeal.INTERSTITIAL);
			}
		});
	}

	public static void showBannerAd(){
		Log.v(TAG, "Appodeal -- showBannerAd");


		PTAdHeyzapBridge.s_activity.get().runOnUiThread( new Runnable() {
			public void run() {
				Appodeal.show(PTAdHeyzapBridge.activity, bannerPosition);
			}
		});
	}

	public static void hideBannerAd(){
		Log.v(TAG, "Appodeal -- hideBannerAd");


		PTAdHeyzapBridge.s_activity.get().runOnUiThread( new Runnable() {
			public void run() {
				Appodeal.hide(PTAdHeyzapBridge.activity, bannerPosition);
			}
		});
	}

	public static boolean isBannerVisible() {
		return Appodeal.getBannerView(PTAdHeyzapBridge.activity).isShown();
		//return (PTAdHeyzapBridge.bannerAdView.getVisibility() == View.VISIBLE);
	}

	public static boolean isRewardedVideoAvialable(){

		return Appodeal.isLoaded(Appodeal.REWARDED_VIDEO);
	}
}
