package com.dh.hospedagem.repository;

import com.dh.hospedagem.DTO.ProductDTO;
import com.dh.hospedagem.model.Category;
import com.dh.hospedagem.model.Cidade;
import com.dh.hospedagem.model.Product;
import com.dh.hospedagem.model.Reserva;
import com.dh.hospedagem.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //
    @Query(value = "SELECT n.name FROM Product n JOIN Category c WHERE n.categorias = :id", nativeQuery = true)
    Page obterProdutosPorIdCategoria(Pageable page, Integer id);

    // pesquisa pelo nome do produto
    @Query("FROM Product p " +
        "WHERE LOWER(p.name) like %:cidade% " +
        "OR LOWER(p.description) like %:cidade%")
    Page<Product> obterProdutosPorIdCidade(@Param("cidade") String cidade, Pageable pageable);

    // pesquisa na reserva os produtos que estão com reserva em um período de datas
    @Query(value = "SELECT * FROM produto p INNER JOIN reserva r  WHERE (r.data_inicial_reserva >= ? AND r.data_inicial_reserva <= ? )", nativeQuery = true)
    List<Reserva> obterProdutoData(@Param("dataInicialReserva") LocalDate dataInicialReserva, @Param("dataFinalReserva") LocalDate dataFinalReserva);

    // pesquisa os imoveis em determinada cidade pelo nome
    @Query(value = "select * from product p where p.cidades_id =  (Select c.id from cidade c where c.name like %:cidade% )", nativeQuery = true)
    List<Product> obterProdutosPorCidade(@Param("cidade") String cidade);

    // corrigindo erros nessa consulta
    @Query(value = "SELECT n.name, c.name FROM Product n JOIN Category c WHERE n.categorias = :id", nativeQuery = true)
    Optional<Object> obterProdutosPorIdCategoria(Integer id);
}
