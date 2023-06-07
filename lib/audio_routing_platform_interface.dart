import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'audio_routing_method_channel.dart';

abstract class AudioRoutingPlatform extends PlatformInterface {
  /// Constructs a AudioRoutingPlatform.
  AudioRoutingPlatform() : super(token: _token);

  static final Object _token = Object();

  static AudioRoutingPlatform _instance = MethodChannelAudioRouting();

  /// The default instance of [AudioRoutingPlatform] to use.
  ///
  /// Defaults to [MethodChannelAudioRouting].
  static AudioRoutingPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AudioRoutingPlatform] when
  /// they register themselves.
  static set instance(AudioRoutingPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

    Future<void> startAudioRouteing() async {
    throw UnimplementedError('startAudioRouteing() has not been implemented.');
  }

  Future<void> stopAudioRouteing() async {
    throw UnimplementedError('stopAudioRouteing() has not been implemented.');
  }

  Future<void> resetAudioRouteing() async {
    throw UnimplementedError('resetAudioRouteing() has not been implemented.');
  }
}
