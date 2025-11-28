import 'dart:convert';
import 'package:flutter/foundation.dart' show kIsWeb;

import 'platform_config_stub.dart'
    if (dart.library.html) 'platform_config_web.dart';

class ApiConfig {
  /// Final base URL used by the app at runtime.
  static late final String baseUrl;

  static const Duration connectionTimeout = Duration(seconds: 30);
  static const Duration receiveTimeout = Duration(seconds: 30);

  static const Map<String, String> defaultHeaders = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  };

  /// Initialize configuration. This will try these options in order:
  /// 1. On web: fetch /config.json and look for { "API_BASE_URL": "..." }
  /// 2. On web: check window.localStorage['API_BASE_URL']
  /// 3. Build-time dart-define: API_BASE_URL
  /// 4. Fallback default http://localhost:8080
  static Future<void> init() async {
    // 3) build-time value
    final buildTime = const String.fromEnvironment(
      'API_BASE_URL',
      defaultValue: 'http://localhost:8080',
    );

    // 1) try to load /config.json (web only)
    if (kIsWeb) {
      try {
        final jsonText = await loadConfigJson();
        if (jsonText != null && jsonText.isNotEmpty) {
          final m = jsonDecode(jsonText) as Map<String, dynamic>;
          final candidate = m['API_BASE_URL'] as String?;
          if (candidate != null && candidate.isNotEmpty) {
            baseUrl = candidate;
            return;
          }
        }
      } catch (_) {
        // ignore and fallback to other options
      }

      // 2) try localStorage
      try {
        final ls = readLocalStorageApiBase();
        if (ls != null && ls.isNotEmpty) {
          baseUrl = ls;
          return;
        }
      } catch (_) {}
    }

    // default to build-time value
    baseUrl = buildTime;
  }
}

class ApiEndpoints {
  static const String auth = '/api/auth';
  static const String login = '$auth/login';
  static const String register = '$auth/register';
  static const String refreshToken = '$auth/refresh';
  static const String logout = '$auth/logout';
  
  static const String users = '/api/users';
  static const String institutions = '/api/institutions';
  static const String branches = '/api/branches';
  static const String students = '/api/students';
  static const String courses = '/api/courses';
  static const String enrollments = '/api/enrollments';
  static const String payments = '/api/payments';
  static const String reports = '/api/reports';
  static const String notifications = '/api/notifications';
}
