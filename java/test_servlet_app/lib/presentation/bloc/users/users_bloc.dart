// ignore: depend_on_referenced_packages


// ignore: depend_on_referenced_packages
import 'package:bloc/bloc.dart';
import 'package:dio/dio.dart';
import 'package:equatable/equatable.dart';
import 'package:test_servlet_app/data/data_manager.dart';
import 'package:test_servlet_app/data/remote_models/user.dart';
import 'package:test_servlet_app/data/remote_models/user_insert_request.dart';
import 'package:test_servlet_app/injection_component.dart';


part 'users_event.dart';
part 'users_state.dart';

class UsersBloc extends Bloc<UsersEvent, UsersState> {

  final DataManager dataManager;
  
  UsersBloc() 
  : dataManager = InjectionComponent.getDependency<DataManager>(),
  super(UsersLoading()) {
    on<UsersEvent>(_onEvent);
  }

  late List<User> _users;
  late int typeId;

  Future<void>? _onEvent(
    UsersEvent event,
    Emitter<UsersState> emit,
  ) {
    if (event is UsersInit) return  _onUsersInit(event, emit);
    if (event is UsersAddUser) return _onAddUser(event, emit);
    if (event is UsersUpdateUser) return _onUpdateUser(event, emit);
    if (event is UsersDeleteUser) return _onDeleteUser(event, emit);
    return null;
  }


  Future<void> _onUsersInit(
    UsersInit event,
    Emitter<UsersState> emit,
  ) async {

    emit(UsersLoading());
    
    try {
      final usersWrapper = await dataManager.getUsersRequest();
      _users = usersWrapper.collectionModel?.embedded?.users ?? [
      const User(
          id: 22, blocked: true)
      ];

      emit(UsersLoaded(users: _users));
    } on DioException catch (e) {
      emit(UsersError(error: e.response));
    }  
 }

  Future<void> _onAddUser(
    UsersAddUser event,
    Emitter<UsersState> emit,
  ) async {

    emit(UsersLoading());

    try {
      final userWrapper = await dataManager.addUser(body: UserActionRequestWrapper(user: event.userInsertBody));
      final user =  userWrapper.collectionModel?.embedded?.users.first;
      _users.insert(0, user ?? User.empty());

      emit(const UsersActionSuccessMessage(message: 'Пользователь добавлен'));
      
      emit(UsersLoaded(
        users: _users,    
      ));      

    } on DioException catch (e) {
      emit(UsersError(error: e.response));
      emit(UsersLoaded(users: _users));
    }  
  }

  Future<void> _onUpdateUser(
    UsersUpdateUser event,
    Emitter<UsersState> emit,
  ) async {

    emit(UsersLoading());

    try {
      final updatedUser = await dataManager.updateUser(
        userId: event.userId, 
        body: UserActionRequestWrapper(user: event.userUpdateBody)
      );
      
      final index = _users.indexWhere((e) => e.id == event.userId);
        if (index > -1) {
          _users[index] = updatedUser.collectionModel?.embedded?.users.first ?? User.empty();
        }

      emit(const UsersActionSuccessMessage(message: 'Пользователь отредактирован'));
      
      emit(UsersLoaded(
        users: _users,    
      ));
    } on DioException catch (e) {
      emit(UsersError(error: e.response));
      emit(UsersLoaded(users: _users));
    }  
  }


  Future<void> _onDeleteUser(
    UsersDeleteUser event,
    Emitter<UsersState> emit,
  ) async {
    emit(UsersLoading());
    try {
      var message =  await dataManager.deleteUser(
        userId:  event.userId
      );
     emit(UsersActionSuccessMessage(message: message));
     _users.removeWhere((e) => e.id == event.userId);
     emit(UsersLoaded(users: _users));
    } on DioException catch (e) {
      emit(UsersError(error: e.response));
      emit(UsersLoaded(users: _users));
    }    
  }
}


