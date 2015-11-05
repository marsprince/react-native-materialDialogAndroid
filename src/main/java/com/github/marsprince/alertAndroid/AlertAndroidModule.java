package com.github.marsprince.alertAndroid;

import android.app.Activity;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.MapBuilder;
import com.github.marsprince.alertAndroid.MaterialDialog;

import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableMap;

import android.view.View;
import android.support.annotation.Nullable;
import android.content.DialogInterface;

/**
 * {@link NativeModule} that allows JS to open the default browser
 *  for an url.
 */
public class AlertAndroidModule extends ReactContextBaseJavaModule {

  private Activity mActivity;
  private ReactApplicationContext reactContext;

  public AlertAndroidModule(ReactApplicationContext reactContext,Activity activity) {
    super(reactContext);
    reactContext=reactContext;
    mActivity = activity;
  }

  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         @Nullable WritableMap params) {
    try {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
    } catch(Exception e){

    }
  }

  @Override
  public String getName() {
    return "AlertAndroid";
  }

  @ReactMethod
  public void show(String title,String message,String positiveButton,String negativeButton) {
    final WritableMap params = Arguments.createMap();
    final MaterialDialog mMaterialDialog = new MaterialDialog(mActivity);
    mMaterialDialog.setTitle(title)
             .setMessage(message)
             .setPositiveButton(positiveButton, new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         mMaterialDialog.dismiss();
                         sendEvent(getReactApplicationContext(), "positiveButtonClick",params);

                     }
                 })
              .setNegativeButton(negativeButton, new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          mMaterialDialog.dismiss();
                          sendEvent(getReactApplicationContext(), "negativeButtonClick",params);
                      }
                  });
         mMaterialDialog.show();
  };
  @ReactMethod
    public void showWithConfig(ReadableMap config) {
      final WritableMap params = Arguments.createMap();
      final MaterialDialog mMaterialDialog = new MaterialDialog(mActivity);
      if(config.hasKey("canceledOnTouchOutside"))
      {
            mMaterialDialog.setCanceledOnTouchOutside(config.getBoolean("canceledOnTouchOutside"));
      };
      if(config.hasKey("title"))
      {
          mMaterialDialog.setTitle(config.getString("title"));
      };
      if(config.hasKey("message"))
      {
          mMaterialDialog.setMessage(config.getString("message"));
      };
      if(config.hasKey("positiveButton"))
      {
      mMaterialDialog.setPositiveButton(config.getString("positiveButton"), new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                mMaterialDialog.dismiss();
                sendEvent(getReactApplicationContext(), "positiveButtonClick",params);

                }
           });
      };
      if(config.hasKey("negativeButton"))
      {
        mMaterialDialog.setNegativeButton(config.getString("negativeButton"), new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                  mMaterialDialog.dismiss();
                  sendEvent(getReactApplicationContext(), "negativeButtonClick",params);
                  }
            });
      };
      mMaterialDialog.setOnDismissListener(new DialogInterface.OnDismissListener(){
            @Override
            public void onDismiss(DialogInterface dialog) {
                sendEvent(getReactApplicationContext(),"onDismissListener",params);
            }
      });
      mMaterialDialog.show();
    }
}
