import 'package:flutter/material.dart';
import 'package:test_servlet_app/data/remote_models/user.dart';
import 'package:test_servlet_app/utils/modal_dialogs.dart';

import 'screens/user_action_screen.dart';

mixin ModalRoutes {
  static Future<void> toUserAction({
    required BuildContext context,
    required User user
  })  {
    ScaffoldMessenger.of(context).removeCurrentSnackBar();
    return ModalDialogs.showScrollableDialog<void>(
      context: context,
      builder: (context) => UserActionScreen(user: user),
    );
  }
}