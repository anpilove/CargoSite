package com.CargoSite.repositories;

import com.CargoSite.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE CONCAT(p.id, ' ', p.title, ' ', p.author , ' ', p.dateCreated) LIKE %?1%")
    List<Post> search(String keyword);
}
