import 'package:equatable/equatable.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:test_servlet_app/domain/entity/user_type_role.dart';

import 'links_model.dart';


part 'user.g.dart';

@JsonSerializable(includeIfNull: false)
class User extends Equatable {

  @JsonKey(name: 'user_id')
  final int? id;

  @JsonKey(name: 'name')
  final String? name;

  @JsonKey(name: 'login')
  final String? login;

  @JsonKey(name: 'password')
  final String? password;

  @JsonKey(name: 'blocked')
  final bool? blocked;

  @JsonKey(name: 'type_id')
  final UserTypeRoleById? type;

  @JsonKey(name: 'type_name')
  final String? description;

  @JsonKey(name: 'date_add')
  final int? dateAdd;

  @JsonKey(name: 'date_blocked')
  final int? dateBlocked;

  @JsonKey(name: 'phone_number')
  final String? phoneNumber;

  @JsonKey(name: 'email')
  final String? email;

  @JsonKey(name: 'blocked_name')
  final String? blockedName;

  @JsonKey(name: 'password_expire')
  final int? passwordExpire;

  @JsonKey(name: 'date_change_password')
  final int? dateChangePassword;

  @JsonKey(name: '_links')
  final Links? links;


  const User({
    required this.id,
    this.name,
    this.login,
    this.password,
    required this.blocked,
    this.type,
    this.description,
    this.dateAdd,
    this.dateBlocked,
    this.phoneNumber,
    this.email,
    this.blockedName,
    this.passwordExpire,
    this.dateChangePassword,
    this.links
  });

  factory User.empty() =>  const User(id: 0, blocked: false);
  factory User.fromJson(Map<String, dynamic> json) => _$UserFromJson(json);

  Map<String, dynamic> toJson() => _$UserToJson(this);

  int get statusSortIndex => (blocked ?? false) ? 1 : 0;

  @override
  List<Object?> get props => [id, name, login, password, blocked, type, 
    description, dateAdd, dateBlocked, phoneNumber, email, blockedName, passwordExpire, dateChangePassword
  ];

  User copyWith({
    int? id,
    String? name,
    String? login,
    String? password,
    bool? blocked,
    UserTypeRoleById? type,
    String? description,
    int? dateAdd,
    int? dateBlocked,
    String? phoneNumber,
    String? email,
    String? blockedName,
    int? passwordExpire,
    int? dateChangePassword,
  }) => User(
      id: id ?? this.id,
      name: name ?? this.name,
      login: login ?? this.login,
      password: password ?? this.password,
      blocked: blocked ?? this.blocked,
      type: type ?? this.type,
      description: description ?? this.description,
      dateAdd: dateAdd ?? this.dateAdd,
      dateBlocked: dateBlocked ?? this.dateBlocked,
      phoneNumber: phoneNumber ?? this.phoneNumber,
      email: email ?? this.email,
      blockedName: blockedName ?? this.blockedName,
      passwordExpire: passwordExpire ?? this.passwordExpire,
      dateChangePassword: dateChangePassword ?? this.dateChangePassword,
    );
}

