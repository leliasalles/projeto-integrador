package com.dh.hospedagem.repository;

import com.dh.hospedagem.model.Funcao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncaoRepository extends CrudRepository<Funcao, Integer> {
}
