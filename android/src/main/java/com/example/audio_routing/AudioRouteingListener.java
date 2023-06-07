package com.example.audio_routing;

import android.util.Log;
import android.widget.Toast;

import cn.rongcloud.rtc.wrapper.constants.RCRTCIWAudioDeviceType;
import cn.rongcloud.rtc.wrapper.listener.IRCRTCIWAudioRouteingListener;

public class AudioRouteingListener implements IRCRTCIWAudioRouteingListener {
    @Override
    public void onAudioDeviceRouted(RCRTCIWAudioDeviceType rcrtciwAudioDeviceType) {
        String tipString = "";

        switch (rcrtciwAudioDeviceType) {
            case PHONE:
                tipString = "听筒";
                break;
            case SPEAKER:
                tipString = "扬声器";
                break;
            case WIRED_HEADSET:
                tipString = "有线耳机";
                break;
            case BLUETOOTH_HEADSET:
                tipString = "蓝牙耳机";
                break;
        }
        Log.d("AudioRouteingListener", "onAudioDeviceRouted: " + tipString);
    }
 
    @Override
    public void onAudioDeviceRouteFailed(RCRTCIWAudioDeviceType rcrtciwAudioDeviceType, RCRTCIWAudioDeviceType rcrtciwAudioDeviceType1) {
        Log.d("AudioRouteingListener", "onAudioDeviceRouteFailed: " + rcrtciwAudioDeviceType + " " + rcrtciwAudioDeviceType1);
    }
}
