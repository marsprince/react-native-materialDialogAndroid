package com.github.marsprince.alertAndroid;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.app.Activity;

import com.github.marsprince.alertAndroid.AlertAndroidModule;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.views.view.ReactViewManager;
import com.facebook.react.uimanager.ViewManager;

public class AlertAndroidPackage implements ReactPackage {

  private Activity mActivity = null;

  public AlertAndroidPackage(Activity activity) {
      mActivity = activity;
  }
  @Override
  public List<NativeModule> createNativeModules(
  ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<NativeModule>();
    modules.add(new AlertAndroidModule(reactContext,mActivity));
    return modules;
  }
  @Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }
}
