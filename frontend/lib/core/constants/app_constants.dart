class AppConstants {
  static const String appName = 'Education ERP';
  static const String appVersion = '1.0.0';
  
  static const String accessTokenKey = 'access_token';
  static const String refreshTokenKey = 'refresh_token';
  static const String userKey = 'user_data';
  
  static const int pageSize = 20;
  static const int maxRetries = 3;
  
  static const Duration splashDuration = Duration(seconds: 2);
  static const Duration snackBarDuration = Duration(seconds: 3);
}

class UserRoles {
  static const String superAdmin = 'SUPER_ADMIN';
  static const String institutionAdmin = 'INSTITUTION_ADMIN';
  static const String branchManager = 'BRANCH_MANAGER';
  static const String courseManager = 'COURSE_MANAGER';
  static const String accountant = 'ACCOUNTANT';
  static const String reportViewer = 'REPORT_VIEWER';
  static const String teacher = 'TEACHER';
  static const String student = 'STUDENT';
  static const String parent = 'PARENT';
  
  static String getRoleDisplayName(String role) {
    switch (role) {
      case superAdmin:
        return 'Süper Admin';
      case institutionAdmin:
        return 'Kurum Yöneticisi';
      case branchManager:
        return 'Şube Müdürü';
      case courseManager:
        return 'Kurs Yöneticisi';
      case accountant:
        return 'Muhasebeci';
      case reportViewer:
        return 'Rapor Görüntüleyici';
      case teacher:
        return 'Öğretmen';
      case student:
        return 'Öğrenci';
      case parent:
        return 'Veli';
      default:
        return role;
    }
  }
}
