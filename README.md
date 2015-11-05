# react-native-materialdialog-android

An AlertDialog for Android,followed [Material Design][md],native code here[source].

[md]: http://www.google.com/design/spec/material-design/introduction.html
[source]:https://github.com/drakeet/MaterialDialog

### Installation

```bash
npm install --save react-native-materialdialog-android
```

### Add it to your android project

* In `android/setting.gradle`

```gradle
...

include ':app'

include ':AlertAndroid'

project(':AlertAndroid').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-materialdialog-android')
```

* In `android/app/build.gradle`

```gradle
...
dependencies {
    ...
    compile project(':AlertAndroid')
}
```

* register module (in MainActivity.java)

```java
import com.github.marsprince.alertAndroid.AlertAndroidPackage;;  // <--- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ......

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new AlertAndroidPackage(this))            // <------ add here
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "ExampleRN", null);

    setContentView(mReactRootView);
  }

  ......

}
```

## Example

* Basic:

```javascript
var alert=require('react-native-materialdialog-android')

alert.show("title","message","OK","Cancel")
```

* More flexible:

```javascript
var alert=require('react-native-materialdialog-android')

var config={
            title:"title",
            message:"message",
            positiveButton:"OK",
            negativeButton:"Cancel",
            canceledOnTouchOutside:true
        }
alert.showWithConfig(config)
```

* Event listen:

There are some listeners which can be used:

positiveButtonClick

negativeButtonClick

onDismissListener

example:

```javascript
DeviceEventEmitter.addListener('positiveButtonClick', function(e: Event) {
        ToastAndroid.show("positiveButtonClick",ToastAndroid.SHORT)// handle event.
        });
        DeviceEventEmitter.addListener('negativeButtonClick', function(e: Event) {
            ToastAndroid.show("negativeButtonClick",ToastAndroid.SHORT)// handle event.
        });
        DeviceEventEmitter.addListener('onDismissListener', function(e: Event) {
            ToastAndroid.show("dismiss",ToastAndroid.SHORT)// handle event.
        });
```

## Version

v1.0.0

## License

MIT
