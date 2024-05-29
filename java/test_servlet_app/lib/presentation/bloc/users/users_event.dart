part of 'users_bloc.dart';

sealed class UsersEvent extends Equatable {
  const UsersEvent();

  @override
  List<Object?> get props => [];
}

class UsersInit extends UsersEvent {}


class UsersAddUser extends UsersEvent {
  final UserActionRequest userInsertBody;

  const UsersAddUser({
    required this.userInsertBody
  });

  @override
  List<Object?> get props => [userInsertBody];
}

class UsersUpdateUser extends UsersEvent {
  final int userId;
  final UserActionRequest userUpdateBody;

  const UsersUpdateUser({
    required this.userUpdateBody,
    required this.userId
  });

  @override
  List<Object?> get props => [userUpdateBody, userId];
}

class UsersDeleteUser extends UsersEvent {
  final int userId;

  const UsersDeleteUser(this.userId);

  @override
  List<Object?> get props => [userId];
}