# Education ERP (Backend: Spring Boot · Frontend: Flutter)

Çok kiracılı (multi-tenant) eğitim ERP sistemi. Backend Spring Boot 3.2.x (Java 21), frontend Flutter Web.

## Özellikler
- Kimlik Doğrulama: JWT (login, register, refresh, logout, validate)
- Rol Tabanlı Yetkilendirme: SUPER_ADMIN, INSTITUTION_ADMIN, BRANCH_MANAGER, COURSE_MANAGER, ACCOUNTANT, REPORT_VIEWER, TEACHER, STUDENT, PARENT
- Modüller: Kullanıcı, Kurum/Şube, Öğrenci, Kurs/Müfredat, Ödeme, Envanter, Bildirim, Rapor
- Dokümantasyon ve İzleme: SpringDoc OpenAPI (Swagger UI), Actuator Health

## Teknolojiler
- Java 21 (LTS), Spring Boot 3.2.1
- JPA/Hibernate, H2 (geliştirme) | PostgreSQL (opsiyonel prod)
- JWT (jjwt 0.12.x), Caffeine Cache
- Maven, Flutter (web)

## Hızlı Başlangıç

- Gereksinimler:
  - Java 21, Maven 3.9+, Flutter SDK, Chrome

- Backend (dev, H2):
  ```powershell
  cd backend
  mvn clean package -DskipTests
  java -jar target\education-erp-1.0.0.jar
  ```
  - Uygulama: http://localhost:8080
  - Health: http://localhost:8080/actuator/health
  - Swagger: http://localhost:8080/swagger-ui.html
  - Varsayılan profil: dev, H2 in-memory (ddl-auto=create-drop)

- Frontend (web):
  ```powershell
  cd frontend
  flutter run -d chrome
  ```
  - Varsayılan `API_BASE_URL`: http://localhost:8080
  - Web için build-time override:
    ```powershell
    flutter run -d chrome --dart-define=API_BASE_URL=http://localhost:8080
    ```
  - Web runtime `config.json` desteği mevcut (frontend/web/config.sample.json).

## Kimlik Doğrulama

- Endpoints:
  - POST `/api/auth/login`
  - POST `/api/auth/register`
  - POST `/api/auth/refresh`
  - POST `/api/auth/logout`
  - POST `/api/auth/validate`

- Test kullanıcıları (DataLoader ile başlatılır):
  - admin / admin123 (SUPER_ADMIN)
  - teacher / teacher123 (TEACHER)
  - student / student123 (STUDENT)

- Örnek login isteği (PowerShell):
  ```powershell
  $body = @{ username="admin"; password="admin123" } | ConvertTo-Json
  Invoke-RestMethod -Method Post -Uri http://localhost:8080/api/auth/login -ContentType "application/json" -Body $body
  ```

## Konfigürasyon

- Backend (`backend/src/main/resources/application.properties`):
  - H2 dev:
    - `spring.datasource.url=jdbc:h2:mem:testdb`
    - `spring.jpa.hibernate.ddl-auto=create-drop`
  - JWT:
    - `jwt.secret` (env ile override edilebilir)
    - Access/refresh süreleri ms cinsinden

- Frontend (`frontend/lib/core/config/api_config.dart`):
  - `ApiConfig.init()` sırasıyla şu kaynaklardan `baseUrl` belirler:
    1) Web’de `/config.json` (varsa),
    2) Web’de `localStorage['API_BASE_URL']`,
    3) `--dart-define=API_BASE_URL=...`,
    4) Varsayılan: `http://localhost:8080`.

## Geliştirme Notları
- CORS: `SecurityConfig` içinde `CorsConfigurationSource` ile `*` izinli (dev için uygun).
- H2 in-memory olduğu için veriler yeniden başlatmada sıfırlanır (dev odaklıdır). Kalıcılık için PostgreSQL’e geçilebilir.
- Swagger UI ve Actuator dev profilinde açık tutulmuştur.

## Çalışır Durum Özeti (28 Kasım 2025)
- Backend: Java 21 ile derleniyor ve 8080’de çalışıyor (Health: UP).
- Frontend: Flutter Web Chrome’da çalışıyor (login ekranı aktif).
- Auth akışları: Login ve Register test edilip token üretimi doğrulandı.
