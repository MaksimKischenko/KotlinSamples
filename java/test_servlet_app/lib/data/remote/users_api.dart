import 'package:retrofit/retrofit.dart';
import 'package:dio/dio.dart';
import 'package:test_servlet_app/data/remote_models/user_insert_request.dart';
import 'package:test_servlet_app/data/remote_models/users_wrapper.dart';

part 'users_api.g.dart';


@RestApi(baseUrl:'http://192.168.244.136:9090/users')
abstract class UsersApi {
factory UsersApi (Dio dio, {String baseUrl}) = _UsersApi;

  @GET('')
  Future<UsersWrapper> getUsers();

  @DELETE('/{userId}')
    Future<String> deleteUser({
    @Path('userId') required int id
  });
  
  @POST('')
    Future<UsersWrapper> addUser({
    @Body() required UserActionRequestWrapper body
  });

  @PUT('/{userId}')
    Future<UsersWrapper> updateUser({
    @Path('userId') required int id,
    @Body() required UserActionRequestWrapper body
  });
}


