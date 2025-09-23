package com.gerenciador_produtos.repository;

import com.gerenciador_produtos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
