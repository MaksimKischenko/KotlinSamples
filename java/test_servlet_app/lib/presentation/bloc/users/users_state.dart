part of 'users_bloc.dart';

sealed class UsersState extends Equatable {
  const UsersState();
  
  @override
  List<Object?> get props => [];
}

final class UsersLoading extends UsersState {}


class UsersLoaded extends UsersState {
  final List<User> users;
  final int? typeId;

  const UsersLoaded({
    required this.users,
    this.typeId
  });

  @override
  List<Object?> get props => [users, typeId];
}

final class UsersActionSuccessMessage extends UsersState {
  final String message;

  const UsersActionSuccessMessage({
    required this.message,
  });

  @override
  List<Object?> get props => [message];  
}

class UsersError extends UsersState {
  final Object? error;

  const UsersError({this.error});

  @override
  List<Object?> get props => [error];
}
