import 'audio_routing_platform_interface.dart';

class AudioRouting {
  Future<String?> getPlatformVersion() {
    return AudioRoutingPlatform.instance.getPlatformVersion();
  }

  Future<void> startAudioRouteing() async {
    return AudioRoutingPlatform.instance.startAudioRouteing();
  }

  Future<void> stopAudioRouteing() async {
    return AudioRoutingPlatform.instance.stopAudioRouteing();
  }

  Future<void> resetAudioRouteing() async {
    return AudioRoutingPlatform.instance.resetAudioRouteing();
  }
}
