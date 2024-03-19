package com.example.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.example.projeto.dtos.ImovelDTO;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.service.ImovelService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
public class ImovelController {

    @Autowired
    private ImovelService service;

    // POST /imoiveis - cria um novo imovel
    // String descrição, Integer quartos, Integer vagas, Integer userId
    @PostMapping("/imoveis")
    public ResponseEntity<ImovelModel> createImovel(@Valid @RequestBody ImovelDTO dto) {
        // ImovelModel model = user.transformaParaObjeto();
        // model = service.insert(model);
        // return
        // ResponseEntity.status(HttpStatus.CREATED).body(ImovelDTOResposta.transformaEmDTO(model));
        ImovelModel imovel = service.transformaParaObjeto(dto);
        imovel = service.insert(imovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(imovel);  
    }

    // GET /moveis - retorna todos os Imoveis
    @GetMapping("/imoveis")
    public ResponseEntity<List<ImovelModel>> getAllImovels() {
        List<ImovelModel> list = service.getAll();
        // List<ImovelModel> listDTO = list.stream().map(imovel -> new
        // ImovelDTOResposta(imovel))
        // .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    // // GET /users/{id} - retorna um usuário específico
    // @GetMapping("/users/{id}")
    // public ResponseEntity<ImovelModel> getImovel(@PathVariable Integer id) {
    // ImovelModel model = service.find(id);
    // return ResponseEntity.status(HttpStatus.OK).body(model);
    // }

    // // PUT /users/{id} - atualiza um usuário específico
    // @PutMapping("/users/{id}")
    // public ResponseEntity<ImovelModel> updateImovel(@RequestBody ImovelModel
    // model, @PathVariable Integer id) {
    // model.setId(id);
    // model = service.update(model);
    // return ResponseEntity.noContent().build();
    // }

    // // DELETE /users/{id} - remove um usuário específico
    // @DeleteMapping("/users/{id}")
    // public ResponseEntity<Void> deleteImovel(@PathVariable Integer id) {
    // service.delete(id);
    // return ResponseEntity.noContent().build();
    // }

    // GET /users/page - retorna uma lista paginada de usuários
    // page: número da página (começa em 0)
    // linesPerPage: quantidade de linhas por página
    // orderBy: campo pelo qual a lista será ordenada
    // direction: direção da ordenação (ASC ou DESC)
    /*
     * @GetMapping("/users/page")
     * public ResponseEntity<Page<ImovelDTOResposta>> getAllImovelsByPage(
     * 
     * @RequestParam(value = "page", defaultValue = "0") Integer page,
     * 
     * @RequestParam(value = "linesw", defaultValue = "10") Integer linesPerPage,
     * 
     * @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
     * 
     * @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
     * Page<ImovelModel> list = service.findPage(page, linesPerPage, orderBy,
     * direction);
     * Page<ImovelDTOResposta> listDTO = list.map(usuario -> new
     * ImovelDTOResposta(usuario));
     * return ResponseEntity.status(HttpStatus.OK).body(listDTO);
     * }
     */

}
