# appodeal--buildbox
Appodeal deal files for buildbox integration

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
