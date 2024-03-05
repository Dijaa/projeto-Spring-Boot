package com.example.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto.models.UserModel;
import com.example.projeto.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserModel> getAll() {
        List<UserModel> list = repository.findAll();
        return list;
    }

    public UserModel find(Integer id) {
        Optional<UserModel> model = repository.findById(id);
        return model.orElse(null);
    }

    public UserModel insert(UserModel model) {
        return repository.save(model);
    }

    public UserModel update(UserModel model) {
        find(model.getId());
        return repository.save(model);
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }

}
