// Web implementation for runtime config helpers
import 'dart:html' as html;

/// Tries to load /config.json placed next to index.html on the web server
Future<String?> loadConfigJson() async {
  try {
    final content = await html.HttpRequest.getString('/config.json');
    return content;
  } catch (_) {
    return null;
  }
}

/// Reads API base URL from window.localStorage if present
String? readLocalStorageApiBase() => html.window.localStorage['API_BASE_URL'];
