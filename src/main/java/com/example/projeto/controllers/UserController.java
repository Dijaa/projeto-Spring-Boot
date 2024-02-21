package com.example.projeto.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.models.UserModel;

@RestController
public class UserController {

    //GET /users - retorna todos os usuários
    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        
        //jpa e hibernate

        List<UserModel> users = new ArrayList<UserModel>();
        users.add(new UserModel(1, "João"));
        users.add(new UserModel(2, "Maria"));
        users.add(new UserModel(3, "eee"));

        return users;
        
    }

}
