import 'package:flutter/material.dart';

enum MessageType {
  error(Colors.redAccent),
  success(Colors.green);

  const MessageType(this.color);
  final Color color;
}
