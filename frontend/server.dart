import 'dart:io';

void main() async {
  final server = await HttpServer.bind('0.0.0.0', 5000);
  print('Server running on http://0.0.0.0:5000');
  
  await for (final request in server) {
    var path = request.uri.path;
    if (path == '/') path = '/index.html';
    
    final file = File('build/web$path');
    
    if (await file.exists()) {
      final contentType = _getContentType(path);
      request.response.headers.contentType = ContentType.parse(contentType);
      await request.response.addStream(file.openRead());
    } else {
      final indexFile = File('build/web/index.html');
      request.response.headers.contentType = ContentType.html;
      await request.response.addStream(indexFile.openRead());
    }
    await request.response.close();
  }
}

String _getContentType(String path) {
  if (path.endsWith('.html')) return 'text/html';
  if (path.endsWith('.css')) return 'text/css';
  if (path.endsWith('.js')) return 'application/javascript';
  if (path.endsWith('.json')) return 'application/json';
  if (path.endsWith('.png')) return 'image/png';
  if (path.endsWith('.jpg') || path.endsWith('.jpeg')) return 'image/jpeg';
  if (path.endsWith('.svg')) return 'image/svg+xml';
  if (path.endsWith('.woff')) return 'font/woff';
  if (path.endsWith('.woff2')) return 'font/woff2';
  if (path.endsWith('.ttf')) return 'font/ttf';
  if (path.endsWith('.otf')) return 'font/otf';
  return 'application/octet-stream';
}
