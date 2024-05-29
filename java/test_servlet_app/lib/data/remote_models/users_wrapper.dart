import 'package:json_annotation/json_annotation.dart';

import 'links_model.dart';
import 'user.dart';

part 'users_wrapper.g.dart';

@JsonSerializable()
class UsersWrapper {

  @JsonKey(name: 'CollectionModel')
  final CollectionModel? collectionModel;


  const UsersWrapper({
    required this.collectionModel,
  });

  factory UsersWrapper.fromJson(Map<String, dynamic> json) => _$UsersWrapperFromJson(json);
  Map<String, dynamic> toJson() => _$UsersWrapperToJson(this);

}

@JsonSerializable()
class CollectionModel {

  @JsonKey(name: '_embedded')
  final Embedded? embedded;

  const CollectionModel({
    required this.embedded,
  });

  factory CollectionModel.fromJson(Map<String, dynamic> json) => _$CollectionModelFromJson(json);
  Map<String, dynamic> toJson() => _$CollectionModelToJson(this);
}


@JsonSerializable()
class Embedded {

  @JsonKey(name: 'users')
  final List<User> users;

  @JsonKey(name: '_links')
  final Links? links;


  const Embedded({
    required this.users,
    required this.links
  });

  factory Embedded.fromJson(Map<String, dynamic> json) => _$EmbeddedFromJson(json);
  Map<String, dynamic> toJson() => _$EmbeddedToJson(this);

}