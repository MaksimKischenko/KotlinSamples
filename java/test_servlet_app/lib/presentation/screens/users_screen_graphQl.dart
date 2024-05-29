
import 'package:flutter/material.dart';
import 'package:graphql_flutter/graphql_flutter.dart';

class UsersScreenGraphQl extends StatefulWidget {
  const UsersScreenGraphQl({super.key});

  @override
  State<UsersScreenGraphQl> createState() => _UsersScreenGraphQlState();
}

class _UsersScreenGraphQlState extends State<UsersScreenGraphQl> {
  @override
  Widget build(BuildContext context) {
    String readUsers = """
    query usersAll {
      allUsers {
        userId
        name
      }
    }
    """;
    return Scaffold(
      body: Query(
        options: QueryOptions(
          document: gql(readUsers), 
          variables: const <String, dynamic>{"variableName": "value"},
        ),
        
        // Just like in apollo refetch() could be used to manually trigger a refetch
        // while fetchMore() can be used for pagination purpose
        builder: (QueryResult result, { VoidCallback? refetch, FetchMore? fetchMore }) {
          if (result.hasException) {
            print('${result.exception}');
              return Text('ERROR${result.exception}');
          }
          if (result.isLoading) {
            return const Text('Loading');
          }
          return Text(result.data.toString());
        },
      ),
    );
  }
}
