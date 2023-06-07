import 'package:flutter_test/flutter_test.dart';
import 'package:audio_routing/audio_routing.dart';
import 'package:audio_routing/audio_routing_platform_interface.dart';
import 'package:audio_routing/audio_routing_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAudioRoutingPlatform
    with MockPlatformInterfaceMixin
    implements AudioRoutingPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final AudioRoutingPlatform initialPlatform = AudioRoutingPlatform.instance;

  test('$MethodChannelAudioRouting is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAudioRouting>());
  });

  test('getPlatformVersion', () async {
    AudioRouting audioRoutingPlugin = AudioRouting();
    MockAudioRoutingPlatform fakePlatform = MockAudioRoutingPlatform();
    AudioRoutingPlatform.instance = fakePlatform;

    expect(await audioRoutingPlugin.getPlatformVersion(), '42');
  });
}
