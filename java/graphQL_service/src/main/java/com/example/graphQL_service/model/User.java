package com.example.graphQL_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "user_", schema = "dev_util")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "date_blocked")
    private String dateBlocked;

    @Column(name = "date_add")
    private String dateAdd;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "failed_login")
    private Long failedLogin;

    @Column(name = "email")
    private String email;

    @Column(name = "password_expire")
    private String passwordExpire;

    @Column(name = "date_change_password")
    private String dateChangePassword;

}
