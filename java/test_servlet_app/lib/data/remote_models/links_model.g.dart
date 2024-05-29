// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'links_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Links _$LinksFromJson(Map<String, dynamic> json) => Links(
      entityHref: json['users'] == null
          ? null
          : EntityHref.fromJson(json['users'] as Map<String, dynamic>),
      selfHref: SelfHref.fromJson(json['self'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$LinksToJson(Links instance) => <String, dynamic>{
      'self': instance.selfHref,
      'users': instance.entityHref,
    };

SelfHref _$SelfHrefFromJson(Map<String, dynamic> json) => SelfHref(
      href: json['href'] as String,
    );

Map<String, dynamic> _$SelfHrefToJson(SelfHref instance) => <String, dynamic>{
      'href': instance.href,
    };

EntityHref _$EntityHrefFromJson(Map<String, dynamic> json) => EntityHref(
      href: json['href'] as String,
    );

Map<String, dynamic> _$EntityHrefToJson(EntityHref instance) =>
    <String, dynamic>{
      'href': instance.href,
    };
