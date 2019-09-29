# appodeal--buildbox
Appodeal deal files for buildbox integration.


Any questions please add them to this forum posts. This post also has the instructions for the AndroidX and newer Gradel.
1. https://www.buildbox.com/forum/index.php?threads/appodeal-integration.15485/

All inside buildbox:
1. Add your Appodeal appId as the heyzap key in the configruation for the app under Android.
2. Configure interstitial, banner and reward ads by selecting Heyzap as the provider.


Android Export Instructions:
1. Add the following to your app build file inside the dependecy brackets:
```
  implementation 'com.appodeal.ads:nodex:2.5.8'
  implementation 'com.google.android.exoplayer:exoplayer-core:2.8.4'
  implementation 'com.google.android.exoplayer:exoplayer-hls:2.8.4'
  implementation 'com.android.support:support-v4:28.0.0'
  implementation 'com.android.support:recyclerview-v7:28.0.0'
```
2. Add the following to your app build file inside the all projects bracket:

```
maven { url "https://artifactory.appodeal.com/appodeal" }
```

3. In the manifest file remove all the Heyzap activities and make sure you add your admob id in the application section.

```
<meta-data android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-xxxxx~xxxxx"/>
            
```
4. Add the follinging to your application section:

```
android:networkSecurityConfig="@xml/network_security_config">
```

5. Add the security file (Thank you GamerGhost). Create a folder named xml in res forder off your app. Create inside this xml folder the network_security_config.xml (right click on res - New - Android Resourse File)

```
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
<base-config cleartextTrafficPermitted="true">
<trust-anchors>
<certificates src="system" />
<certificates src="user" />
</trust-anchors>
</base-config>
<domain-config cleartextTrafficPermitted="true">
<domain includeSubdomains="true">127.0.0.1</domain>
</domain-config>
</network-security-config>
``
6. Replace your file HZAdapter file with the version included in this repo.
