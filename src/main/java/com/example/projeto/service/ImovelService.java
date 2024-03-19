package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.projeto.dtos.ImovelDTO;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.repository.ImovelRepository;
import com.example.projeto.repository.UserRepository;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<ImovelModel> getAll() {
        List<ImovelModel> list = repository.findAll();
        return list;
    }

    public ImovelModel find(Integer id) {
        Optional<ImovelModel> model = repository.findById(id);
        return model.orElse(null);
    }

    public ImovelModel insert(ImovelModel model) {
        return repository.save(model);
    }

    public ImovelModel update(ImovelModel model) {
        find(model.getId());
        return repository.save(model);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Page<ImovelModel> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(request);
    }

    public ImovelModel transformaParaObjeto(ImovelDTO dto) {
        System.out.println("Entrou no transformaParaObjeto");
        System.out.println("descrição: " + dto.getDescrição());
        System.out.println("quartos: " + dto.getQuartos());
        System.out.println("vagas: " + dto.getVagas());
        System.out.println("userId: " + dto.getUserId());
        
        ImovelModel imovel = new ImovelModel();
        imovel.setDescrição(dto.getDescrição());
        imovel.setQuartos(dto.getQuartos());
        imovel.setVagas(dto.getVagas());
        System.out.println("Descrição" + imovel.getDescrição());
        System.out.println("Quartos" + imovel.getQuartos());
        System.out.println("Vagas" + imovel.getVagas());
        UserModel user = userRepository.findById(dto.getUserId()).orElseThrow();
        System.out.println("User" + user);
        imovel.setUser(user);
        System.out.println(imovel);
        return imovel;
    }
}
