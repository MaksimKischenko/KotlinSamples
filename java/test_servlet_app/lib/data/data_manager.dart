import 'package:dio/dio.dart';
import 'package:test_servlet_app/data/remote_models/user_insert_request.dart';
import 'package:test_servlet_app/domain/entity/user_type_role.dart';


import 'remote/users_api.dart';
import 'remote_models/users_wrapper.dart';


class DataManager {
  final UsersApi _usersApi;

  final Dio dio;

  DataManager(this.dio) :
  _usersApi = UsersApi(dio);

  Future<UsersWrapper> getUsersRequest({
    String? productName,
    int? listSize
  }) => _usersApi.getUsers();


  Future<String> deleteUser({
   required int userId,
  }) => _usersApi.deleteUser(id: userId);


  Future<UsersWrapper> addUser({
   required UserActionRequestWrapper body,
  }) => _usersApi.addUser(body: body);


  Future<UsersWrapper> updateUser({
  required int userId,
   required UserActionRequestWrapper body,
  }) => _usersApi.updateUser(
    id: userId,
    body: body
  );

  final userAdmin = UserActionRequest(
    createDate: DateTime.now().millisecondsSinceEpoch,
    name: 'JavaSpringAdmin',
    login: 'JavaSpringAdmin',
    password: '123456',
    typeId: UserTypeRoleById.admin.id,
    blocked: false
  );

  final userSimple = UserActionRequest(
    createDate: DateTime.now().millisecondsSinceEpoch,
    name: 'JavaSpringUser',
    login: 'JavaSpringUser',
    password: '123456',
    typeId: UserTypeRoleById.user.id,
    blocked: false
  );

  final userViewer = UserActionRequest(
    createDate: DateTime.now().millisecondsSinceEpoch,
    name: 'JavaSpringViewer',
    login: 'JavaSpringViewer',
    password: '123456',
    typeId: UserTypeRoleById.userViewer.id,
    blocked: false
  );

  final updateAdmin = UserActionRequest(
    name: 'UpdatedAdmin',
    login: 'UpdatedAdmin',
    password: '123456789',
    typeId: UserTypeRoleById.admin.id,
    blocked: true
  );
}