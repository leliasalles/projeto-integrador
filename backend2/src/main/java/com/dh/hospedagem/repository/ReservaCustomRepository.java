package com.dh.hospedagem.repository;

import com.dh.hospedagem.model.Reserva;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ReservaCustomRepository {
    private final EntityManager entityManager;


    public ReservaCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Reserva> pesquisar(LocalDate data_inicial, LocalDate data_final){
        String query = "SELECT r FROM Reserva as r ";
        String condicao = " WHERE r.data BETWEEN ";

        if(data_inicial != null){
            query += condicao + " r.dataInicialReserva = :dataInicialReserva ";
            condicao = " AND ";
        }
        if(data_final != null){
            query += condicao + " r.dataFinalReserva = :dataFinalReserva ";
        }

        var q = entityManager.createQuery(query, Reserva.class);

        if(data_inicial != null){
            q.setParameter("dataInicialReserva", data_inicial);
        }
        if(data_final != null){
            q.setParameter("dataFinalReserva",data_inicial);
        }
        return q.getResultList();

    }
}
