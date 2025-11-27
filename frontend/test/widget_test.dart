import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:education_erp_frontend/main.dart';

void main() {
  testWidgets('App loads correctly', (WidgetTester tester) async {
    await tester.pumpWidget(const EducationErpApp());
    expect(find.byType(MaterialApp), findsOneWidget);
  });
}
