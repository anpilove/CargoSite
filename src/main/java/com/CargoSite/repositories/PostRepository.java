package com.CargoSite.repositories;

import com.CargoSite.controller.Cargo;
import com.CargoSite.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Cargo, Long> {

}
