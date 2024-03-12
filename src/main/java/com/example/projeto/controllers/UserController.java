package com.example.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.UserDTO;
import com.example.projeto.dtos.UserDTOResposta;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    // POST /users - cria um novo usuário
    @PostMapping("/users")
    public ResponseEntity<UserDTOResposta> createUser(@Valid @RequestBody UserDTO user) {
        UserModel model = user.transformaParaObjeto();
        model = service.insert(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDTOResposta.transformaEmDTO(model));
    }

    // GET /users - retorna todos os usuários
    @GetMapping("/users")
    public ResponseEntity<List<UserDTOResposta>> getAllUsers() {
        List<UserModel> list = service.getAll();
        List<UserDTOResposta> listDTO = list.stream().map(usuario -> new UserDTOResposta(usuario))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(listDTO);

    }

    // GET /users/{id} - retorna um usuário específico
    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Integer id) {
        UserModel model = service.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

    // PUT /users/{id} - atualiza um usuário específico
    @PutMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel model, @PathVariable Integer id) {
        model.setId(id);
        model = service.update(model);
        return ResponseEntity.noContent().build();
    }

    // DELETE /users/{id} - remove um usuário específico
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /users/page - retorna uma lista paginada de usuários
    // page: número da página (começa em 0)
    // linesPerPage: quantidade de linhas por página
    // orderBy: campo pelo qual a lista será ordenada
    // direction: direção da ordenação (ASC ou DESC)
    @GetMapping("/users/page")
    public ResponseEntity<Page<UserDTOResposta>> getAllUsersByPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesw", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<UserModel> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<UserDTOResposta> listDTO = list.map(usuario -> new UserDTOResposta(usuario));
        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

}
