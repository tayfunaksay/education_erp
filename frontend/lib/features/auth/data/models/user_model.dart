class User {
  final int id;
  final String username;
  final String email;
  final String firstName;
  final String lastName;
  final String role;
  final int? institutionId;
  final int? branchId;
  final bool isActive;
  final DateTime? createdAt;
  final DateTime? updatedAt;

  User({
    required this.id,
    required this.username,
    required this.email,
    required this.firstName,
    required this.lastName,
    required this.role,
    this.institutionId,
    this.branchId,
    this.isActive = true,
    this.createdAt,
    this.updatedAt,
  });

  String get fullName => '$firstName $lastName';

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'] ?? 0,
      username: json['username'] ?? '',
      email: json['email'] ?? '',
      firstName: json['firstName'] ?? '',
      lastName: json['lastName'] ?? '',
      role: json['role'] ?? '',
      institutionId: json['institutionId'],
      branchId: json['branchId'],
      isActive: json['isActive'] ?? true,
      createdAt: json['createdAt'] != null ? DateTime.parse(json['createdAt']) : null,
      updatedAt: json['updatedAt'] != null ? DateTime.parse(json['updatedAt']) : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'username': username,
      'email': email,
      'firstName': firstName,
      'lastName': lastName,
      'role': role,
      'institutionId': institutionId,
      'branchId': branchId,
      'isActive': isActive,
    };
  }
}

class AuthResponse {
  final String accessToken;
  final String refreshToken;
  final User user;
  final String tokenType;
  final int expiresIn;

  AuthResponse({
    required this.accessToken,
    required this.refreshToken,
    required this.user,
    this.tokenType = 'Bearer',
    required this.expiresIn,
  });

  factory AuthResponse.fromJson(Map<String, dynamic> json) {
    final data = json['data'] ?? json;
    return AuthResponse(
      accessToken: data['token'] ?? data['accessToken'] ?? '',
      refreshToken: data['refreshToken'] ?? '',
      tokenType: data['tokenType'] ?? 'Bearer',
      expiresIn: data['expiresIn'] ?? 0,
      user: User.fromJson({
        'id': 0, // Backend'den dönen response'da user detayları yok, username'den alıyoruz
        'username': data['username'] ?? '',
        'email': data['username'] ?? '',  // Şimdilik username'i email olarak kullan
        'firstName': '',
        'lastName': '',
        'role': data['role'] ?? '',
        'isActive': true,
      }),
    );
  }
}

class LoginRequest {
  final String username;
  final String password;

  LoginRequest({required this.username, required this.password});

  Map<String, dynamic> toJson() => {'username': username, 'password': password};
}

class RegisterRequest {
  final String email;
  final String password;
  final String firstName;
  final String lastName;
  final String role;
  final int? institutionId;
  final int? branchId;

  RegisterRequest({
    required this.email,
    required this.password,
    required this.firstName,
    required this.lastName,
    required this.role,
    this.institutionId,
    this.branchId,
  });

  Map<String, dynamic> toJson() => {
    'email': email,
    'password': password,
    'firstName': firstName,
    'lastName': lastName,
    'role': role,
    if (institutionId != null) 'institutionId': institutionId,
    if (branchId != null) 'branchId': branchId,
  };
}
