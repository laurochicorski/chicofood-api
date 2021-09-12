package com.chicorski.chicofoodapi.domain.repository;

import com.chicorski.chicofoodapi.domain.filter.VendaDiariaFilter;
import com.chicorski.chicofoodapi.domain.model.Pedido;
import com.chicorski.chicofoodapi.domain.model.dto.VendaDiaria;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

    Optional<Pedido> findByCodigo(String codigo);

    @Query("from Pedido p join fetch p.cliente c join fetch p.restaurante r join fetch r.cozinha cz")
    List<Pedido> findAll();

    boolean isPedidoGerenciadoPor(String codigoPedido, Long usuarioId);
}