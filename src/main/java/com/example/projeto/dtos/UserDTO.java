package com.example.projeto.dtos;

import org.hibernate.validator.constraints.Length;

import com.example.projeto.models.UserModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class UserDTO{
    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotBlank(message = "Nome não pode ser vazio")
    @Length(min = 10, max = 255, message = "Nome deve ter entre 10 e 255 caracteres")
    private String nome;

    @NotEmpty(message = "Senha obrigatória")
    @NotBlank(message = "Senha não pode ser vazia")
    @Length(min = 4, max = 10, message = "Senha deve ter entre 4 e 10 caracteres")
    private String senha;

    public UserDTO(){}

    public UserModel transformaParaObjeto(){
        return new UserModel(nome, senha);
    }
}