package com.gerenciador_produtos.repository;

import com.gerenciador_produtos.model.Categoria;
import com.gerenciador_produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Busca uma lista de produtos que possuem exatamente este nome no banco
    List<Produto> findByNome(String nome);

    // Retornar o produto que contem o nome no banco
    Optional<Produto> findByNomeContainingIgnoreCase(String nome);

    // Buscar a lista de produtos relacionados com o nome da categoria no banco
    List<Produto> findByCategoria(Categoria categoria);

    // Maior que o preço fornecido
    List<Produto> findByPrecoGreaterThan(Double preco);

    // Menor que o preço fornecido
    List<Produto> findByPrecoLessThan(Double preco);

    List<Produto> findByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

    List<Produto> findByCategoriaNomeOrderByPrecoDesc(String categoriaNome);

    long countByCategoria(Categoria categoria);

    long countByPrecoGreaterThan(Double preco);

    List<Produto> findByPrecoLessThanOrNomeContaining(Double preco, String termo);

    List<Produto> findTop3ByPrecoDesc();

    List<Produto> findTop5ByCategoriaNomeOrderByPrecoAsc(String categoriaNome);
}
