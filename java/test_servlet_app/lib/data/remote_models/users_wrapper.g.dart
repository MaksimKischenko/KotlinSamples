// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'users_wrapper.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UsersWrapper _$UsersWrapperFromJson(Map<String, dynamic> json) => UsersWrapper(
      collectionModel: json['CollectionModel'] == null
          ? null
          : CollectionModel.fromJson(
              json['CollectionModel'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$UsersWrapperToJson(UsersWrapper instance) =>
    <String, dynamic>{
      'CollectionModel': instance.collectionModel,
    };

CollectionModel _$CollectionModelFromJson(Map<String, dynamic> json) =>
    CollectionModel(
      embedded: json['_embedded'] == null
          ? null
          : Embedded.fromJson(json['_embedded'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$CollectionModelToJson(CollectionModel instance) =>
    <String, dynamic>{
      '_embedded': instance.embedded,
    };

Embedded _$EmbeddedFromJson(Map<String, dynamic> json) => Embedded(
      users: (json['users'] as List<dynamic>)
          .map((e) => User.fromJson(e as Map<String, dynamic>))
          .toList(),
      links: json['_links'] == null
          ? null
          : Links.fromJson(json['_links'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$EmbeddedToJson(Embedded instance) => <String, dynamic>{
      'users': instance.users,
      '_links': instance.links,
    };
