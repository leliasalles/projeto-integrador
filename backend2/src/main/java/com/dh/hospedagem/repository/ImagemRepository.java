package com.dh.hospedagem.repository;

import com.dh.hospedagem.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {

//    @Query(value = "SELECT i FROM Imagem i JOIN FETCH i.products")
    @Override
    List<Imagem> findAll();
}
