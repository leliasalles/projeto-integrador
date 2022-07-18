package com.dh.hospedagem.repository;

import com.dh.hospedagem.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    Optional<Cidade> findByName(String name);
}
