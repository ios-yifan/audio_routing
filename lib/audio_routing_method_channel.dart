import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'audio_routing_platform_interface.dart';

/// An implementation of [AudioRoutingPlatform] that uses method channels.
class MethodChannelAudioRouting extends AudioRoutingPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('audio_routing');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override 
  Future<void> startAudioRouteing() async {
    return await methodChannel.invokeMethod('startAudioRouteing');
  }
  @override 
  Future<void> stopAudioRouteing() async {
    return await methodChannel.invokeMethod('stopAudioRouteing');
  }
  @override 
  Future<void> resetAudioRouteing() async {
    return await methodChannel.invokeMethod('resetAudioRouteing');
  }
}
