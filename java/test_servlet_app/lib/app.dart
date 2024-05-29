

import 'package:flutter/material.dart';
import 'package:graphql_flutter/graphql_flutter.dart';

import 'presentation/routes.dart';

class App extends StatefulWidget {
  const App({super.key});

  @override
  State<App> createState() => _AppState();
}

class _AppState extends State<App> {
  
  final HttpLink httpLink = HttpLink(
    'http://localhost:8080/graphiql?path=/graphql',
  );
  late ValueNotifier<GraphQLClient> client;
 
  @override
  void initState() {
   client = ValueNotifier(
      GraphQLClient(
        link: httpLink,
        cache: GraphQLCache(store: HiveStore()),
      ),
    );
    super.initState();
  }

  @override
  Widget build(BuildContext context) => GraphQLProvider(
    client: client,
    child: MaterialApp.router(
      debugShowCheckedModeBanner: false,
      routerConfig: router, 
      theme: ThemeData.light(
        useMaterial3: true
      ),
    ),
  );
}
