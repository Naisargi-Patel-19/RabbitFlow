package com.rabbit.dto;

import lombok.Data;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
}
