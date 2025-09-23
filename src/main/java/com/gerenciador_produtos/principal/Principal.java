package com.gerenciador_produtos.principal;

import com.gerenciador_produtos.model.Categoria;
import com.gerenciador_produtos.model.Pedido;
import com.gerenciador_produtos.model.Produto;
import com.gerenciador_produtos.repository.CategoriaRepository;
import com.gerenciador_produtos.repository.PedidoRepository;
import com.gerenciador_produtos.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private PedidoRepository pedidoRepository;

    public Principal (ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                1 - Produtos
                2 - Categorias
                3 - Pedidos
                
                0 - Sair                                 
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    carregarProdutos();
                    break;
                case 2:
                    carregarCategorias();
                    break;
                case 3:
                    carregarPedidos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void carregarProdutos() {
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                    1 - Criar Produto
                    2 - Buscar Produtos
                    
                    0 - Voltar ao Menu Principal
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    criarProduto();
                    break;
                case 2:
                    buscarProdutos();
                    break;
                case 0:
                    System.out.println("Voltando...");
            }
        }
    }
    private void carregarCategorias() {
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                    1 - Criar Categoria
                    2 - Buscar Categorias
                    
                    0 - Voltar ao Menu Principal
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    criarCategoria();
                    break;
                case 2:
                    buscarCategorias();
                    break;
                case 0:
                    System.out.println("Voltando...");
            }
        }
    }
    private void carregarPedidos() {
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                    1 - Criar Pedido
                    2 - Buscar Pedidos
                    
                    0 - Voltar ao Menu Principal
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    criarPedido();
                    break;
                case 2:
                    buscarPedidos();
                    break;
                case 0:
                    System.out.println("Voltando...");
            }
        }
    }

    private void criarProduto(){
        System.out.println("Digite o nome do produto: ");
        String produtoNome = leitura.nextLine();

        System.out.println("Digite o preço do produto: ");
        Double produtoPreco = leitura.nextDouble();

        Produto novoProduto = new Produto(produtoNome, produtoPreco);
        produtoRepository.save(novoProduto);
        System.out.println("Produto criado com sucesso!");
    }

    private void criarCategoria(){
        System.out.println("Digite o nome da categoria: ");
        String categoriaNome = leitura.nextLine();

        Categoria novaCategoria = new Categoria(categoriaNome);
        categoriaRepository.save(novaCategoria);
        System.out.println("Categoria criada com sucesso!");
    }

    private void criarPedido(){
        System.out.println("Digite a data do pedido (yyyy-mm-dd): ");
        String entradaDataPedido = leitura.nextLine();

        LocalDate dataPedido = LocalDate.parse(entradaDataPedido);

        Pedido novoPedido = new Pedido(dataPedido);
        pedidoRepository.save(novoPedido);
        System.out.println("Categoria criada com sucesso!");
    }

    private void buscarProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        System.out.println(produtos);
    }

    private void buscarCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        System.out.println(categorias);
    }

    private void buscarPedidos(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        System.out.println(pedidos);
    }
}
