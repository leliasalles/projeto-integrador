package com.dh.hospedagem.repository;

import com.dh.hospedagem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// tipo de entidade e tipo do id de Category
// é um Repositorio por isso o Repository, injetando dependencias
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
//    List<Category> findByName(String nome);
//    List<Category> findByNameContem(String nome);
    Optional<Category> findByName(String nome);
}
