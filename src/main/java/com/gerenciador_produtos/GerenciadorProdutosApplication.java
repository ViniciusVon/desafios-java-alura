package com.gerenciador_produtos;

import com.gerenciador_produtos.principal.Principal;
import com.gerenciador_produtos.repository.CategoriaRepository;
import com.gerenciador_produtos.repository.PedidoRepository;
import com.gerenciador_produtos.repository.ProdutoRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorProdutosApplication implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach((entry) -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(GerenciadorProdutosApplication.class, args);
	}

    public void run(String... args) throws Exception {
        Principal principal = new Principal(produtoRepository, categoriaRepository, pedidoRepository);
        principal.exibeMenu();
    }

}
