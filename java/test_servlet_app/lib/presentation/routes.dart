import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'screens/users_screen.dart';
import 'screens/users_screen_graphQl.dart';


final router = GoRouter(
  initialLocation: '/users_',//'/users_',
  debugLogDiagnostics: true, 

  observers: [
    GoRouterObserver()
  ],
  routes: [
    GoRoute(
      path: '/users_',
      pageBuilder: (context, state) => CustomTransitionPage<void>(
        key: state.pageKey,
        transitionsBuilder: (context, animation, secondaryAnimation, child) =>
            FadeTransition(opacity: animation, child: child),
        child: UsersScreen(),   
      ),
    ),       
    GoRoute(
      path: '/users_graphQL',
      pageBuilder: (context, state) => CustomTransitionPage<void>(
        key: state.pageKey,
        transitionsBuilder: (context, animation, secondaryAnimation, child) =>
            FadeTransition(opacity: animation, child: child),
        child: const UsersScreenGraphQl(),   
      ),
    ),          
  ],
);

class GoRouterObserver extends NavigatorObserver {
  // @override
  // void didPush(Route<dynamic> route, Route<dynamic>? previousRoute) {
  //   log('MyTest didPush: ${previousRoute?.settings.name}');
  // }

  // @override
  // void didPop(Route<dynamic> route, Route<dynamic>? previousRoute) {
  //   print('MyTest didPop: $route');
  // }

  // @override
  // void didRemove(Route<dynamic> route, Route<dynamic>? previousRoute) {
  //   print('MyTest didRemove: $route');
  // }

  // @override
  // void didReplace({Route<dynamic>? newRoute, Route<dynamic>? oldRoute}) {
  //   print('MyTest didReplace: $newRoute');
  // }
}