# education_erp_frontend

A new Flutter project.

## Getting Started

This project is a starting point for a Flutter application.

A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://docs.flutter.dev/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://docs.flutter.dev/cookbook)

For help getting started with Flutter development, view the
[online documentation](https://docs.flutter.dev/), which offers tutorials,
samples, guidance on mobile development, and a full API reference.

## Runtime configuration for API_BASE_URL

This frontend supports multiple ways to configure the `API_BASE_URL` used to contact the backend:

- Build-time (all platforms): use `--dart-define` when building/running. Example:

```bash
# debug / run
flutter run --dart-define=API_BASE_URL=https://api.example.com

# build for web
flutter build web --dart-define=API_BASE_URL=https://api.example.com
```

- Web runtime config: the web build can be provided a `/config.json` file next to `index.html` with contents like:

```json
{
	"API_BASE_URL": "https://api.example.com"
}
```

	If `/config.json` exists it takes precedence. You can also set `API_BASE_URL` in `window.localStorage`.

Default fallback is `http://localhost:8080`.
