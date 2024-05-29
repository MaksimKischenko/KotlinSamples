import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:test_servlet_app/data/data_manager.dart';
import 'package:test_servlet_app/domain/entity/message_type.dart';
import 'package:test_servlet_app/injection_component.dart';
import 'package:test_servlet_app/presentation/bloc/users/users_bloc.dart';
import 'package:test_servlet_app/presentation/modal_routes.dart';

class UsersScreen extends StatelessWidget {
  final DataManager dataManager;
  UsersScreen({super.key}) : dataManager = InjectionComponent.getDependency<DataManager>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: [
          IconButton(
            tooltip: 'Администратор',
            onPressed: () {
              context.read<UsersBloc>().add(
                UsersAddUser(userInsertBody: dataManager.userAdmin)
              );
            }, 
            icon: const Icon(Icons.add_box_outlined, color: Colors.purpleAccent)
          ),
          IconButton(
            tooltip: 'Пользователь',
            onPressed: () {
              context.read<UsersBloc>().add(
                UsersAddUser(userInsertBody: dataManager.userSimple)
              );              
            }, 
            icon: const Icon(Icons.add_box_outlined, color: Colors.blueAccent)
          ), 
          IconButton(
            tooltip: 'Обозреватель',
            onPressed: () {
              context.read<UsersBloc>().add(
                UsersAddUser(userInsertBody: dataManager.userViewer)
              );              
            }, 
            icon: const Icon(Icons.add_box_outlined, color: Colors.green)
          ),                     
        ],
        title: const Text('Пользователи'),
      ),
      body: BlocConsumer<UsersBloc, UsersState>(
        listener: (context, state) {
          if(state is UsersError) {
            showMessage(
              messageType: MessageType.error,
              message: state.error.toString(), 
              context: context
            );            
          } if (state is UsersActionSuccessMessage) {
            showMessage(
              messageType: MessageType.success,
              message: state.message, 
              context: context
            );   
          }         
        },
        builder: (context, state) {
          Widget body = const SizedBox.shrink();
          if(state is UsersLoading) {
            body = const Center(
              child: CircularProgressIndicator(
                backgroundColor: Colors.blueAccent,
              ),
            );
          } else if (state is UsersLoaded) {
            body = ListView.builder(
              itemCount: state.users.length,
              shrinkWrap: true, 
              itemBuilder: (context, index) {
                final isBlocked = state.users[index].blocked ?? false;
                return ListTile(
                  onTap: () {
                    ModalRoutes.toUserAction(
                      context: context, 
                      user: state.users[index]
                    );
                  },
                  title: Text(
                    '${state.users[index].type?.name }',
                    style: const TextStyle(
                      fontSize: 14,
                      fontWeight: FontWeight.w600
                    ),
                  ),
                  subtitle: Text(
                    '${state.users[index].login}',
                    style: TextStyle(
                      fontSize: 14,
                      color: Colors.grey.shade600,
                      fontWeight: FontWeight.w600
                    ),
                  ),
                  leading: isBlocked? 
                  const Icon(Icons.block_outlined, color: Colors.red):
                  const Icon(Icons.check_circle_outlined, color: Colors.green),
                  trailing: Column(
                    mainAxisSize: MainAxisSize.min,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                       Text(
                        'Создан:',
                        style: TextStyle(
                          fontSize: 12,
                          color: Colors.grey.shade600,
                          fontWeight: FontWeight.w600
                        ),                        
                      ),
                      Text(
                        '${state.users[index].dateAdd}',
                        style: const TextStyle(
                          fontSize: 12,
                          color: Colors.blueGrey,
                          fontWeight: FontWeight.w600
                        ),                             
                      ),
                    ],
                  ),
                );
              },
            );
          } else if(state is UsersError) {
            body = const Center(
              child: Icon(
                Icons.error_outline_sharp, 
                color: Colors.red, 
                size: 120
              )
            );
          }
          return body;
        },
      ),
    );
  }

  void showMessage({
    required MessageType messageType,
    required String message,
    required BuildContext context
  }) {
      ScaffoldMessenger.of(context).removeCurrentSnackBar();
     final snackBar = SnackBar(
      content: Center(
        child: Text(message, textAlign: TextAlign.center)
      ),
      elevation: 10,
      duration: const Duration(seconds: 5),
      backgroundColor: messageType.color,
      dismissDirection: DismissDirection.up
    );


    ScaffoldMessenger.of(context).showSnackBar(snackBar);   
  }
}
