package com.example.projeto.dtos;

import java.io.Serializable;

import com.example.projeto.models.UserModel;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImovelDTO implements Serializable{
    
    private Integer id;

    private String descrição;

    private Integer quartos;

    private Integer vagas;

    private Integer userId;    


}