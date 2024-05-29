import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';

part 'user_add_event.dart';
part 'user_add_state.dart';

class UserAddBloc extends Bloc<UserAddEvent, UserAddState> {
  UserAddBloc() : super(UserAddInitial()) {
    on<UserAddEvent>((event, emit) {
      // TODO: implement event handler
    });
  }
}
