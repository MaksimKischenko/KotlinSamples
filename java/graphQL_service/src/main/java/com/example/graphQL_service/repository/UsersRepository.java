package com.example.graphQL_service.repository;

import com.example.graphQL_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UsersRepository")
public interface UsersRepository extends JpaRepository<User, Long> {

}