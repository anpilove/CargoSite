package com.CargoSite.services;

import com.CargoSite.models.UserCargo;
import com.CargoSite.repositories.UserCargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCargoService {
    @Autowired
    private UserCargoRepository repo;

    public List<UserCargo> listAll(String keyword){
        if (keyword != null){
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(UserCargo usercargo){
        repo.save(usercargo);
    }

    public void delete(String login){
        repo.deleteByLogin(login);
    }

    public boolean checkLoginAndPassword(UserCargo usercargo) {
        return repo.existsByLoginAndPassword(usercargo.getLogin(), usercargo.getPassword());
    }

}
