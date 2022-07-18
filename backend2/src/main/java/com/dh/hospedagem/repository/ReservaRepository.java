package com.dh.hospedagem.repository;

import com.dh.hospedagem.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

     List<Reserva> findBydataInicialReserva(LocalDate dataInicialReserva);

     // JPQL
     @Modifying
     @Query(value = "SELECT r.id, r.data_final_reserva FROM reserva r WHERE r.product_id = :id", nativeQuery = true)
     List<Reserva> findByReservasPorIdDoProduto(Integer id);


     @Query(value = "SELECT * FROM reserva r  WHERE (r.data_inicial_reserva >= ? AND r.data_inicial_reserva <= ?)", nativeQuery = true)
     List<Reserva> obterReservasData(@Param("dataInicialReserva") LocalDate dataInicialReserva, @Param("dataFinalReserva") LocalDate dataFinalReserva);


     @Query(value = "SELECT * FROM reserva r  WHERE r.product_id = ?", nativeQuery = true)
     List<Reserva> obterReservasPorIdProduto(@Param("id") Integer id);


     @Query(value = "SELECT * FROM reserva r WHERE r.usuario_id = ? ", nativeQuery = true)
     List<Reserva> obterReservaPorIdUsuario(@Param("id") Integer id);

}
