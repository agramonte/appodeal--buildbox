# appodeal--buildbox
Appodeal deal files for buildbox integration.  
Tested on:  
Appodeal Version: 2.6.3  
Buildbox: 2.3.8

Video can be found here: https://youtu.be/LgEXMw6pUvM
(The video is now older than the documentation. You can use it as a general idea but please follow this read-me).

Any questions please add them to this forum posts. This post also has the instructions for the AndroidX and newer Gradel.
1. https://www.buildbox.com/forum/index.php?threads/appodeal-integration.15485/

All inside buildbox:
1. Add your Appodeal appId as the heyzap key in the configruation for the app under Android.
2. Configure interstitial, banner and reward ads by selecting Heyzap as the provider.


Android Export Instructions:
1. Add the following to your app build file inside the dependecy brackets:
```
implementation ('com.appodeal.ads:sdk:2.6.3.+') {
        exclude group: 'com.appodeal.ads.sdk.networks', module: 'smaato'
    }
```  

2. Add the following to your app build file inside the all projects bracket project build file:
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
```
6. Replace your file HZAdapter file with the version included in this repo.  

7. Delete the file PTAdHeyzap.jar from the "<ProjectExportFolder>/app/libs/". If you leave this library in that folder your game/app will be removed from the store by Google. (Thank you Murtaza Kohinoor youtube subscriber for reporting this issue).
