
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import 'presentation/bloc/users/users_bloc.dart';

class AppWrapper extends StatefulWidget {
  final Widget child;

  const AppWrapper({
    Key? key,
    required this.child,
  }) : super(key: key);

  @override
  State<AppWrapper> createState() => _AppWrapperState();
}

class _AppWrapperState extends State<AppWrapper> {
  @override
  Widget build(BuildContext context) => MultiBlocProvider(
    providers: [           
      BlocProvider<UsersBloc>(
        create: (context) => UsersBloc()..add(UsersInit()),
      ),                        
    ],
    child: widget.child
  );
}
