class ApiConfig {
  static const String baseUrl = String.fromEnvironment(
    'API_BASE_URL',
    defaultValue: 'http://localhost:8080',
  );
  
  static const Duration connectionTimeout = Duration(seconds: 30);
  static const Duration receiveTimeout = Duration(seconds: 30);
  
  static const Map<String, String> defaultHeaders = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  };
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
