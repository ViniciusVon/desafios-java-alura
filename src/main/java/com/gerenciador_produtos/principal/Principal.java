package com.gerenciador_produtos.principal;

import com.gerenciador_produtos.model.Categoria;
import com.gerenciador_produtos.model.Fornecedor;
import com.gerenciador_produtos.model.Pedido;
import com.gerenciador_produtos.model.Produto;
import com.gerenciador_produtos.repository.CategoriaRepository;
import com.gerenciador_produtos.repository.FornecedorRepository;
import com.gerenciador_produtos.repository.PedidoRepository;
import com.gerenciador_produtos.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    List<Categoria> categorias = new ArrayList<>();
    List<Produto> produtos = new ArrayList<>();

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private PedidoRepository pedidoRepository;
    private FornecedorRepository fornecedorRepository;

    public Principal (ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, PedidoRepository pedidoRepository, FornecedorRepository fornecedorRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.pedidoRepository = pedidoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                1 - Produtos
                2 - Categorias
                3 - Pedidos
                4 - Fornecedores
                
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
                case 4:
                    carregarFornecedores();
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
                    2 - Buscar Produtos Pelo Nome
                    3 - Buscar Todos Produtos
                    
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
                    buscarProdutosNome();
                    break;
                case 3:
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

    private void carregarFornecedores() {
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                    1 - Criar Fornecedor
                    2 - Buscar Fornecedores
                    
                    0 - Voltar ao Menu Principal
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    criarFornecedor();
                    break;
                case 2:
                    buscarFornecedores();
                    break;
                case 0:
                    System.out.println("Voltando...");
            }
        }
    }

    private void criarProduto(){
        buscarCategorias();
        System.out.println("Digite a categoria que desja inserir o produto: ");
        String categoria = leitura.nextLine();
        Optional<Categoria> firstCategoria = categorias.stream()
                .filter(c -> c.getNome().toLowerCase().contains(categoria.toLowerCase()))
                .findFirst();

        if(firstCategoria.isPresent()){
            var categoriaEncontrada = firstCategoria.get();

            System.out.println("Digite o nome do produto: ");
            String produtoNome = leitura.nextLine();

            System.out.println("Digite o preço do produto: ");
            Double produtoPreco = leitura.nextDouble();

            Produto novoProduto = new Produto(produtoNome, produtoPreco, categoriaEncontrada);

            Fornecedor fornecedorBase = new Fornecedor("Elma Chips");
            fornecedorRepository.save(fornecedorBase);
            novoProduto.setFornecedor(fornecedorBase);
            produtoRepository.save(novoProduto);
            System.out.println("Produto criado com sucesso!");
        } else {
            System.out.println("Nenhuma categoria encontrada!");
        }
    }

    private void criarCategoria(){
        System.out.println("Digite o nome da categoria: ");
        String categoriaNome = leitura.nextLine();

        Categoria novaCategoria = new Categoria(categoriaNome);
        categoriaRepository.save(novaCategoria);
        System.out.println("Categoria criada com sucesso!");
    }

    private void criarPedido(){
        buscarProdutos();
        System.out.println("Digite o nome do produto que deseja realizar o pedido: ");
        String produtoNome = leitura.nextLine();

        Optional<Produto> firstProduto = produtos.stream()
                .filter(p -> p.getNome().toLowerCase().contains(produtoNome.toLowerCase()))
                .findFirst();

        if(firstProduto.isPresent()){
            var produtoEncontrado = firstProduto.get();
            LocalDate dataPedido = LocalDate.now();

            Pedido novoPedido = new Pedido(dataPedido);
            novoPedido.setProdutos(List.of(produtoEncontrado));

            pedidoRepository.save(novoPedido);
            System.out.println("Pedido criado com sucesso!");
        } else {
            System.out.println("Pedido não encontrado!");
        }
    }

    private void criarFornecedor(){
        System.out.println("Digite o nome do fornecedor: ");
        String fornecedorNome = leitura.nextLine();

        Fornecedor novoFornecedor = new Fornecedor(fornecedorNome);
        fornecedorRepository.save(novoFornecedor);
        System.out.println("Categoria criada com sucesso!");
    }

    private void buscarProdutos(){
        produtos = produtoRepository.findAll();
        System.out.println(produtos);
    }

    private void buscarCategorias(){
        categorias = categoriaRepository.findAll();
        System.out.println(categorias);
    }

    private void buscarPedidos(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        System.out.println(pedidos);
    }

    private void buscarFornecedores(){
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        System.out.println(fornecedores);
    }

    private void buscarProdutosNome(){
        System.out.println("Digite o nome do produto: ");
        String produtoNome = leitura.nextLine();
        Optional<Produto> produtoBuscado = produtoRepository.findByNomeContainingIgnoreCase(produtoNome);

        if(produtoBuscado.isPresent()){
            System.out.println("Produto Buscado: " +  produtoBuscado.get());
        } else {
            System.out.println("Nenhum produto encontrado no sistema!");
        }
    }
}
