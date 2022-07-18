package com.dh.hospedagem.repository;

import com.dh.hospedagem.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductCustomRepository{
    private final EntityManager entityManager;

    public ProductCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<Product> pesquisa(Integer id, String nome){
        String query = "SELECT p FROM Product as p ";
        String where = " WHERE ";

        if( id != null ){
            query += where + " p.id = :id";
            where = " AND ";
        }
        if (nome != null){
            query += where + " p.name = :nome";
        }

        var q  = entityManager.createQuery(query, Product.class);

        if (id != null){
            q.setParameter("id", id);
        }
        if(nome != null){
            q.setParameter("nome", nome);
        }

        return  q.getResultList();
    }
}
