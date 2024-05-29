import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:go_router/go_router.dart';
import 'package:test_servlet_app/data/data_manager.dart';

import 'package:test_servlet_app/data/remote_models/user.dart';
import 'package:test_servlet_app/injection_component.dart';
import 'package:test_servlet_app/presentation/bloc/users/users_bloc.dart';

class UserActionScreen extends StatelessWidget {
  final User user;
  final DataManager dataManager;

  UserActionScreen({
    Key? key,
    required this.user,
  }) : 
  dataManager = InjectionComponent.getDependency<DataManager>(),
  super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Row(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          IconButton(
            onPressed: () {
              _onDeleteUser(context, user.id ?? 0);
            }, 
            icon: const Icon(Icons.delete_forever, color: Colors.redAccent)
          ),
          IconButton(
            onPressed: () {
              _onUpdateUser(context, user.id ?? 0);
            }, 
            icon: const Icon(Icons.edit, color: Colors.amberAccent)
          ),                       
        ],
      ),
    );
  }

  void _onDeleteUser(BuildContext context, int id) {
    context.read<UsersBloc>().add(UsersDeleteUser(id));
    context.pop();
  }

  void _onUpdateUser(BuildContext context, int id) {
    context.read<UsersBloc>().add(UsersUpdateUser(userId: id, userUpdateBody: dataManager.updateAdmin));
    context.pop();
  }
}
