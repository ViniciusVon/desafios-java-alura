package com.gerenciador_produtos.repository;

import com.gerenciador_produtos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    List<Pedido> findByDataEntregaIsNull();

    List<Pedido> findByDataEntregaIsNotNull();

    List<Pedido> findByDataPedidoAfter(LocalDate data);

    List<Pedido> findByDataPedidoBefore(LocalDate data);

    List<Pedido> findByDataPedidoBetween(LocalDate dataInicio, LocalDate dataFim);
}
