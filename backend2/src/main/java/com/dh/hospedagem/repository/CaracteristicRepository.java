package com.dh.hospedagem.repository;

import com.dh.hospedagem.model.Caracteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaracteristicRepository extends JpaRepository<Caracteristic, Integer> {

//    @Query(value = "SELECT c FROM Caracteristic c JOIN FETCH c.products")
//    @Override
//    List<Caracteristic> findAll();
    Optional<Caracteristic> findByName(String name);
}
