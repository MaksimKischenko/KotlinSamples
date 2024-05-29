import 'package:json_annotation/json_annotation.dart';

part 'user_insert_request.g.dart';

@JsonSerializable(includeIfNull: false)
class UserActionRequestWrapper{

  @JsonKey(name: 'User')
  final UserActionRequest? user;

  const UserActionRequestWrapper({
    this.user,
  });

  factory UserActionRequestWrapper.fromJson(Map<String, dynamic> json) => _$UserActionRequestWrapperFromJson(json);

  Map<String, dynamic> toJson() => _$UserActionRequestWrapperToJson(this);
} 

@JsonSerializable(includeIfNull: false)
class UserActionRequest {

  @JsonKey(name: 'date_add')
  final int? createDate;

  @JsonKey(name: 'name')
  final String? name;

  @JsonKey(name: 'login')
  final String? login;

  @JsonKey(name: 'type_id')
  final int? typeId;

  @JsonKey(name: 'password')
  final String? password;

  @JsonKey(name: 'blocked')
  final bool? blocked;

  const UserActionRequest({
    this.createDate,
    this.name,
    this.login,
    this.typeId,
    this.password,
    this.blocked = false,
  });


  factory UserActionRequest.empty() =>  const UserActionRequest();

  factory UserActionRequest.fromJson(Map<String, dynamic> json) => _$UserActionRequestFromJson(json);

  Map<String, dynamic> toJson() => _$UserActionRequestToJson(this);
  

  UserActionRequest copyWith({
    int? createDate,
    String? name,
    String? login,
    int? typeId,
    String? password,
    bool? blocked,
  }) => UserActionRequest(
      createDate: createDate ?? this.createDate,
      name: name ?? this.name,
      login: login ?? this.login,
      typeId: typeId ?? this.typeId,
      password: password ?? this.password,
      blocked: blocked ?? this.blocked,
    );
}
