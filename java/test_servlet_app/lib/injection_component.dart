
import 'dart:io';

import 'package:dio/dio.dart';
import 'package:dio/io.dart';
import 'package:dio_http_formatter/dio_http_formatter.dart';
import 'package:flutter/foundation.dart';
import 'package:injector/injector.dart';

import 'data/data_manager.dart';

class InjectionComponent {
  static final InjectionComponent _singleton = InjectionComponent._internal();
  // Use this static instance
  static final Injector injector = Injector.appInstance;

  factory InjectionComponent() => _singleton;

  InjectionComponent._internal();

  static Future<void> run() async {


    injector
      ..registerSingleton<Dio>(() {

        final dio = Dio();
        if (!kIsWeb) {
          (dio.httpClientAdapter as IOHttpClientAdapter)
                  .onHttpClientCreate = //TODO this soon will be deprecated so its neeed to be changed to proper way of intaraction
              (HttpClient client) {
            client.badCertificateCallback =
                (X509Certificate cert, String host, int port) => true;
            return client;
          };
        }
        dio.options.connectTimeout = const Duration(milliseconds: 150000);
        dio.options.receiveTimeout = const Duration(milliseconds: 150000);

        dio.interceptors.add(HttpFormatter());

        // dio.interceptors.add(alice.getDioInterceptor());

        return dio;
      })
      ..registerSingleton<DataManager>(() {
        final dio = injector.get<Dio>();
        return DataManager(dio);
        // return FakeDataManager(dio);
      });

    // injector.registerSingleton<PreferencesHelper>((_) => PreferencesHelper());
  }

  static T getDependency<T>() => injector.get<T>();
}
