import 'package:json_annotation/json_annotation.dart';
part 'links_model.g.dart';

@JsonSerializable()
class Links {

  @JsonKey(name: 'self')
  final SelfHref selfHref;

  @JsonKey(name: 'users')
  final EntityHref? entityHref;


  const Links({
    required this.entityHref,
    required this.selfHref
  });

  factory Links.fromJson(Map<String, dynamic> json) => _$LinksFromJson(json);
  Map<String, dynamic> toJson() => _$LinksToJson(this);

}

@JsonSerializable()
class SelfHref {

  @JsonKey(name: 'href')
  final String href;

  const SelfHref({
    required this.href
  });

  factory SelfHref.fromJson(Map<String, dynamic> json) => _$SelfHrefFromJson(json);
  Map<String, dynamic> toJson() => _$SelfHrefToJson(this);

}
@JsonSerializable()
class EntityHref {

  @JsonKey(name: 'href')
  final String href;

  const EntityHref({
    required this.href
  });

  factory EntityHref.fromJson(Map<String, dynamic> json) => _$EntityHrefFromJson(json);
  Map<String, dynamic> toJson() => _$EntityHrefToJson(this);

}