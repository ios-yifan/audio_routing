package com.example.audio_routing;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import cn.rongcloud.rtc.wrapper.constants.RCRTCIWAudioDeviceErrorType;
import cn.rongcloud.rtc.wrapper.constants.RCRTCIWVideoDeviceErrorType;
import cn.rongcloud.rtc.wrapper.flutter.RCRTCEngineWrapper;
import cn.rongcloud.rtc.wrapper.listener.IRCRTCIWLocalDeviceErrorListener;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** AudioRoutingPlugin */
public class AudioRoutingPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native
  /// Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine
  /// and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private AudioRouteingListener audioRouteingListener;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "audio_routing");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("startAudioRouteing")) {
      setLocalDeviceErrorListener();
      startAudioRouteing();
      new Handler(Looper.getMainLooper()).post(new Runnable() {
        @Override
        public void run() {
          result.success(null);
        }
      });
    } else if (call.method.equals("stopAudioRouteing")) {
      stopAudioRouteing();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                result.success(null);
            }
        });
    } else if (call.method.equals("resetAudioRouteing")) {
      resetAudioRouteing();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                result.success(null);
            }
        });
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  private void startAudioRouteing() {
    if (audioRouteingListener == null) {
        audioRouteingListener = new AudioRouteingListener();
    }
    RCRTCEngineWrapper.getInstance().startAudioRouteing(audioRouteingListener);
}

private void stopAudioRouteing() {
    RCRTCEngineWrapper.getInstance().stopAudioRouteing();
    if (audioRouteingListener != null) {
        audioRouteingListener = null;
    }
}

private void resetAudioRouteing() {
//    Toast.makeText(getMainContext(),"重置成功",Toast.LENGTH_SHORT).show();
    RCRTCEngineWrapper.getInstance().resetAudioRouteingState();
    Log.d("AudioRouteingListener", "resetAudioRouteing: ");
}

private int setLocalDeviceErrorListener() {
    return RCRTCEngineWrapper.getInstance().setLocalDeviceErrorListener(new IRCRTCIWLocalDeviceErrorListener(){
        @Override
        public void onAudioDeviceError(RCRTCIWAudioDeviceErrorType type) {
            String tipString = "";
            switch (type) {
                case IN_INTERRUPTION:
                    tipString = "音频采集设备被其他应用抢占";
                    break;
                case END_INTERRUPTION:
                    tipString = "其他应用释放音频设备，尝试恢复音频设备";
                    break;
                case AUDIO_RECORD_START_FAILED:
                    tipString = "开启音频采集设备失败";
                    break;
                default:
                    tipString = "未知错误";
                    break;
            }
            Log.e("Flutter_RTC_Demo", "音频设备错误状态：" + tipString);
        }

        @Override
        public void onVideoDeviceError(RCRTCIWVideoDeviceErrorType type) {
            String tipString = "";
            switch (type) {
                case IN_INTERRUPTION:
                    tipString = "摄像头资源被其他应用抢占";
                    break;
                case END_INTERRUPTION:
                    tipString = "摄像头资源恢复";
                    break;
                case END_CAMERA_ERROR_UNKNOWN:
                    // 对应Camera.ErrorCallback#CAMERA_ERROR_UNKNOWN
                    // 目前应用退后台后，摄像头自动停止采集这种场景Camera会报CAMERA_ERROR_UNKNOWN，应通知开发者
                    tipString = "应用退后台自动停止采集";
                    break;
                default:
                    tipString = "未知错误";
                    break;
            }
            Log.e("Flutter_RTC_Demo", "摄像头错误状态：" + tipString);
        }
    });
}
}
