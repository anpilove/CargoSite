package com.CargoSite.services;

import com.CargoSite.repositories.PostRepository;
import com.CargoSite.models.Post;
import org.springframework.beans.factory.annotation.Autowired; // для связи всех зависимостей в классах
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> listAll(String keyword){
        if (keyword != null){
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(Post post){
        repo.save(post);
    }

    public Post get(Long id){
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
