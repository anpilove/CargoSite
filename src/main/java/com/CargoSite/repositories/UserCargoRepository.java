package com.CargoSite.repositories;

import com.CargoSite.models.UserCargo;
import com.CargoSite.services.PostService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCargoRepository extends JpaRepository<UserCargo, Integer > {
    @Query("SELECT p FROM UserCargo p WHERE CONCAT(p.login, ' ', p.password, ' ', p.role) LIKE %?1%")
    List<UserCargo> search(String keyword);


    PostService findByLogin(String login);

    void deleteByLogin(String login);

    boolean existsByLoginAndPassword(String login, String password);

    boolean existsByLogin(String login);

}
