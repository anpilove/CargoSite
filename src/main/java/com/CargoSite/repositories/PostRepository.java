package com.CargoSite.repositories;

import com.CargoSite.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE CONCAT(p.id, ' ', p.title, ' ', p.author , ' ', p.dateCreated, ' ', p.content) LIKE %?1%")
    List<Post> search(String keyword);

    @Query("SELECT p FROM Post p WHERE p.dateCreated LIKE %?1%")
    List<Post> findByDate(String date);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1%")
    List<Post> findByTitle(String title);

    @Query("SELECT p FROM Post p WHERE CONCAT(p.title, ' ', p.dateCreated) LIKE %?1%")
    List<Post> findByDateAndTitle(String searchTerm);

    @Query("SELECT p FROM Post p WHERE p.content  LIKE %?1%")
    List<Post> findByText(String text);

    @Query("SELECT p FROM Post p WHERE CONCAT(p.content, ' ', p.dateCreated) LIKE %?1%")
    List<Post> findByDateAndText(String searchTerm);

}
