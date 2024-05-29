// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

User _$UserFromJson(Map<String, dynamic> json) => User(
      id: json['user_id'] as int?,
      name: json['name'] as String?,
      login: json['login'] as String?,
      password: json['password'] as String?,
      blocked: json['blocked'] as bool?,
      type: $enumDecodeNullable(_$UserTypeRoleByIdEnumMap, json['type_id']),
      description: json['type_name'] as String?,
      dateAdd: json['date_add'] as int?,
      dateBlocked: json['date_blocked'] as int?,
      phoneNumber: json['phone_number'] as String?,
      email: json['email'] as String?,
      blockedName: json['blocked_name'] as String?,
      passwordExpire: json['password_expire'] as int?,
      dateChangePassword: json['date_change_password'] as int?,
      links: json['_links'] == null
          ? null
          : Links.fromJson(json['_links'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$UserToJson(User instance) {
  final val = <String, dynamic>{};

  void writeNotNull(String key, dynamic value) {
    if (value != null) {
      val[key] = value;
    }
  }

  writeNotNull('user_id', instance.id);
  writeNotNull('name', instance.name);
  writeNotNull('login', instance.login);
  writeNotNull('password', instance.password);
  writeNotNull('blocked', instance.blocked);
  writeNotNull('type_id', _$UserTypeRoleByIdEnumMap[instance.type]);
  writeNotNull('type_name', instance.description);
  writeNotNull('date_add', instance.dateAdd);
  writeNotNull('date_blocked', instance.dateBlocked);
  writeNotNull('phone_number', instance.phoneNumber);
  writeNotNull('email', instance.email);
  writeNotNull('blocked_name', instance.blockedName);
  writeNotNull('password_expire', instance.passwordExpire);
  writeNotNull('date_change_password', instance.dateChangePassword);
  writeNotNull('_links', instance.links);
  return val;
}

const _$UserTypeRoleByIdEnumMap = {
  UserTypeRoleById.user: 2,
  UserTypeRoleById.admin: 1,
  UserTypeRoleById.unknown: 3,
  UserTypeRoleById.userViewer: 4,
};
