import 'package:bloc_concurrency/bloc_concurrency.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:test_servlet_app/app.dart';

import 'app_wrapper.dart';
import 'injection_component.dart';
import 'presentation/simple_bloc_observer.dart';
import 'package:graphql_flutter/graphql_flutter.dart';



Future<void> bootstrap() async {
  await InjectionComponent.run();
  await initHiveForFlutter();
  Bloc.observer = SimpleBlocObserver();
  Bloc.transformer = sequential<dynamic>();    //выбиваем прошлый евент зпуска вебсокета и начинаем новый
  runApp(const AppWrapper(child: App()));      
}

