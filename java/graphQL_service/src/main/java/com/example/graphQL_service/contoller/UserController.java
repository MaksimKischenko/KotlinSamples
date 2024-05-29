package com.example.graphQL_service.contoller;
import com.example.graphQL_service.model.User;
import com.example.graphQL_service.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final UsersRepository repository;

    public UserController(UsersRepository repository) {
        this.repository = repository;
    }
    @QueryMapping
    public List<User> allUsers() {
        return repository.findAll();
    }
}


