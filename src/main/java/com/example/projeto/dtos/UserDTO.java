package com.example.projeto.dtos;

import com.example.projeto.models.UserModel;

import lombok.Getter;


@Getter
public class UserDTO{
    private Integer id;

    private String nome;

    private String senha;

    public UserDTO(){}

    public UserModel transformaParaObjeto(){
        return new UserModel(nome, senha);
    }
}