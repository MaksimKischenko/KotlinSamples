part of 'user_add_bloc.dart';

sealed class UserAddState extends Equatable {
  const UserAddState();
  
  @override
  List<Object> get props => [];
}

final class UserAddInitial extends UserAddState {}
