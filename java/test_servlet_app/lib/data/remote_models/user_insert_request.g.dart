// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_insert_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UserActionRequestWrapper _$UserActionRequestWrapperFromJson(
        Map<String, dynamic> json) =>
    UserActionRequestWrapper(
      user: json['User'] == null
          ? null
          : UserActionRequest.fromJson(json['User'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$UserActionRequestWrapperToJson(
    UserActionRequestWrapper instance) {
  final val = <String, dynamic>{};

  void writeNotNull(String key, dynamic value) {
    if (value != null) {
      val[key] = value;
    }
  }

  writeNotNull('User', instance.user);
  return val;
}

UserActionRequest _$UserActionRequestFromJson(Map<String, dynamic> json) =>
    UserActionRequest(
      createDate: json['date_add'] as int?,
      name: json['name'] as String?,
      login: json['login'] as String?,
      typeId: json['type_id'] as int?,
      password: json['password'] as String?,
      blocked: json['blocked'] as bool? ?? false,
    );

Map<String, dynamic> _$UserActionRequestToJson(UserActionRequest instance) {
  final val = <String, dynamic>{};

  void writeNotNull(String key, dynamic value) {
    if (value != null) {
      val[key] = value;
    }
  }

  writeNotNull('date_add', instance.createDate);
  writeNotNull('name', instance.name);
  writeNotNull('login', instance.login);
  writeNotNull('type_id', instance.typeId);
  writeNotNull('password', instance.password);
  writeNotNull('blocked', instance.blocked);
  return val;
}
