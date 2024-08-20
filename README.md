# Get100 - an Android arithmetic puzzle game
[![CI](https://github.com/cdu278/get100/actions/workflows/ci.yml/badge.svg)](https://github.com/cdu278/get100/actions/workflows/ci.yml)

<img src="./assets/gfx/screenshots/en-US/1.png" alt="1" width="200"> <img src="./assets/gfx/screenshots/en-US/2.png" alt="2" width="200"> <img src="./assets/gfx/screenshots/en-US/3.png" alt="3" width="200"> <img src="./assets/gfx/screenshots/en-US/4.png" alt="4" width="200">

Get100 is an arithmetic puzzle game for Android.
The task is to place the signs of arithmetic operations between the numbers so that you get 100.
The game interface instantly reacts to changes you make to the solution, updating shown result.
Get100 is a great way to practice calculating in your head.

## Building & Installation
You can [import](https://developer.android.com/studio/intro/migrate#import_a_gradle-based_intellij_project) project
in Android Studio and use `Build` > `Generate Signed Bundle / APK...` wizard to build APK.
Alternatively, you can build APK using [command line tools](https://developer.android.com/studio#downloads):
1. Download the source code
```shell
$ git clone https://github.com/cdu278/get100.git
$ cd get100
```

2. Create `keystore.properties` file containing [APK signing](https://developer.android.com/studio/publish/app-signing) credentials:
```
storeFile=/path/to/keystore/file.jks
storePassword=***
keyAlias=<used-key-alias>
keyPassword=***
```

3. Start building
```shell
$ export ANDROID_SDK_ROOT=/path/to/android-sdk
$ ./gradlew assembleReleaseSigned
```

Install APK using [ADB](https://developer.android.com/studio/command-line/adb):
```shell
$ adb install app/build/outputs/apk/releaseSigned/app-releaseSigned.apk
```
or manually on device.
