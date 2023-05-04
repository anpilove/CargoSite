package com.CargoSite.repositories;

import com.CargoSite.models.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CargoRepository extends JpaRepository<Cargo, Long> {
    @Query("SELECT p FROM Cargo p WHERE CONCAT(p.nameLoad, ' ', p.content, ' ', p.cityFrom , ' ', p.cityTo, ' ', p.dataInput, ' ', p.dataOutput) LIKE %?1%")
    List<Cargo> search(String keyword);



}