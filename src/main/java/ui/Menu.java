package ui;

import bd.LojaDAO;
import model.Produto;
import service.BDService;
import service.ResultadoOperacao;

import java.util.List;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        char escolha;

        while (true) {
            System.out.println("\n\n\n");
            System.out.println("Bom dia! você quer: ");
            System.out.println("A) cadastrar produto");
            System.out.println("B) listar produtos");
            System.out.println("C) Buscar Produtos");
            System.out.println("D) Atualizar Produtos");
            System.out.println("E) Deletar Produtos");
            System.out.println("\nX) Sair");

            escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') break;
            else {
                System.out.println("\n\n\n");
                switch (escolha) {
                    case 'A' -> cadastrar();
                    case 'B' -> listar();
                    case 'C' -> buscar();
                    case 'D' -> atualizar();
                    case 'E' -> deletar();
                    default ->
                        System.out.println("\n\n(opção inexistente, tente novamente)\n\n");
                }
            }
        }

        scanner.close();
    }

    public static void espera() {
        System.out.println("(digite 'enter' para continuar)");
        scanner.nextLine();
    }

    public static void aviso() {
        System.out.println("\n\n(digite 'enter' vazio em qualquer campo para voltar)\n\n");
    }

    public static void cadastrar() {
        ResultadoOperacao resutadoOperacao;
        String nome;
        double preco = 0;
        int estoque = 0;

        do {
            aviso();

            System.out.print("Digite o nome do produto: ");
            nome = scanner.nextLine();
            if(nome.isBlank()) break;

            do {
                System.out.print("Digite o preco do produto: ");
                String precoString = scanner.nextLine();
                if (precoString.isBlank()) break;
                else preco = Double.parseDouble(precoString);

                if(!BDService.precoEhVerificado(preco))
                    System.out.println(ResultadoOperacao.mensagem(ResultadoOperacao.PRECO_INVALIDO));
            } while (!BDService.precoEhVerificado(preco));

            do {
                System.out.print("Digite a quantidade no estoque: ");
                String estoqueString = scanner.nextLine();
                if (estoqueString.isBlank()) break;
                else estoque = Integer.parseInt(estoqueString);

                if(!BDService.estoqueEhVerificado(estoque))
                    System.out.println(ResultadoOperacao.mensagem(ResultadoOperacao.ESTOQUE_INVALIDO));
            } while (!BDService.estoqueEhVerificado(estoque));

            resutadoOperacao = BDService.cadastrar(nome, preco, estoque);
            System.out.println(ResultadoOperacao.mensagem(resutadoOperacao));
            espera();
        } while (resutadoOperacao != ResultadoOperacao.SUCESSO);

    }

    public static void listar() {
        System.out.println("\n\n\n");
        List<Produto> produtos = LojaDAO.listar();

        if(!produtos.isEmpty())
            produtos.forEach(System.out::println);
        else
            System.out.println("\n\n(não à produtos na lista)\n\n");
        espera();
    }

    public static void buscar() {
        char escolha;

        while (true){
            System.out.println("\n\n\nDeseja buscar por: ");
            System.out.println("A) id_produto\nB) nome");
            System.out.println("\nX) sair");

            escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') break;
            else {
                System.out.println("\n\n\n");
                switch (escolha) {
                    case 'A' -> buscarPorId();
                    case 'B' -> buscarPorNome();
                    default ->
                            System.out.println("\n\n(opção inexistente, tente novamente)\n\n");
                }
            }
        }
    }

    public static void buscarPorId() {
        aviso();

        int id;
        System.out.print("Digite o ID do produto: ");
        String idString = scanner.nextLine();
        if(idString.isBlank()) return;
        else id = Integer.parseInt(idString);

        Produto produto = LojaDAO.buscaPorId(id);
        if(produto != null) System.out.println(produto);
        else System.out.println("(Nenhum resultado encontrado)\n");
        espera();
    }

    public static void buscarPorNome() {
        aviso();

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        if(nome.isBlank()) return;

        List<Produto> produtos = LojaDAO.buscarPorNome(nome);

        if(!produtos.isEmpty())
            produtos.forEach(System.out::println);

        else
            System.out.println("(Nenhum resultado encontrado)\n");

        espera();
    }

    public static void mostrarDados(Produto produto) {
        System.out.println("\n\n\n");
        System.out.println("Dados:");
        System.out.println(produto);
    }

    public static Produto verificarId() {
        Produto produto;

        while (true) {

            int id;
            System.out.print("Digite o ID do produto: ");
            String idString = scanner.nextLine();
            if(idString.isBlank()) return null;
            else id = Integer.parseInt(idString);

            produto = LojaDAO.buscaPorId(id);

            if (produto != null) break;
            else System.out.println("\n\n(ID inválido, tente novamente)\n\n");
        }

        return produto;
    }

    public static void atualizar() {
        char escolha;
        Produto produto = null;

        while (true){
            if (produto == null) produto = verificarId();
            else {
                System.out.println("Quer trocar o produto?(s/n): ");
                escolha = scanner.next().charAt(0);
                scanner.nextLine();

                if(escolha == 'S') produto = verificarId();
                else produto = LojaDAO.buscaPorId(produto.getIdProduto());
            }

            if(produto == null) return;

            System.out.println("\n\n\n");
            mostrarDados(produto);

            System.out.println("\n\n\nDeseja atualizar: ");
            System.out.println("A) nome\nB) preco\nC) estoque");
            System.out.println("\nX) sair");

            escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();


            if (escolha == 'X') break;
            else {
                switch (escolha) {
                    case 'A' -> atualizarNome(produto);
                    case 'B' -> atualizarPreco(produto);
                    case 'C' -> atualizarEstoque(produto);
                    default ->
                            System.out.println("\n\n(opção inexistente, tente novamente)\n\n");
                }
            }
        }
    }

    public static void atualizarNome(Produto produto) {
        mostrarDados(produto);

        aviso();
        System.out.print("Digite o novo nome do produto: ");
        String nome = scanner.nextLine();
        if(nome.isBlank()) return;

        LojaDAO.atualizarNome(produto.getIdProduto(), nome);
    }

    public static void atualizarPreco(Produto produto) {
        double preco;

        while (true) {
            mostrarDados(produto);
            aviso();

            System.out.print("Digite o novo preco do produto: ");
            String precoString = scanner.nextLine();
            if(precoString.isBlank()) return;
            else preco = Double.parseDouble(precoString);

            if(!BDService.precoEhVerificado(preco))
                System.out.println(ResultadoOperacao.mensagem(ResultadoOperacao.PRECO_INVALIDO));
            else break;
        }

        LojaDAO.atualizarPreco(produto.getIdProduto(), preco);
    }

    public static void atualizarEstoque(Produto produto) {
        while (true) {
            mostrarDados(produto);

            System.out.println("Você quer:");
            System.out.println("A) Acrescentar ao estoque\nB) Decrescentar ao estoque");
            System.out.println("\nX) voltar");

            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') break;
            else {
                switch (escolha) {
                    case 'A' -> {
                        acrescentarEstoque(produto);
                        return;
                    }
                    case 'B' -> {
                        decrescentarEstoque(produto);
                        return;
                    }
                    default -> System.out.println("\n\n(opção inexistente, tente novamente)\n\n");
                }
            }
        }
    }

    public static void acrescentarEstoque(Produto produto) {
        int estoque;

        while (true) {
            mostrarDados(produto);
            aviso();

            System.out.println("Quanto deseja acrescentar?: ");
            String estoqueString = scanner.nextLine();
            if(estoqueString.isBlank()) return;
            else estoque = Integer.parseInt(estoqueString);

            if(!BDService.estoqueEhVerificado(estoque))
                System.out.println(ResultadoOperacao.mensagem(ResultadoOperacao.ESTOQUE_INVALIDO));
            else break;
        }

        LojaDAO.atualizarEstoque(produto.getIdProduto(), estoque);
    }

    public static void decrescentarEstoque(Produto produto) {
        int estoque;

        while (true) {
            mostrarDados(produto);
            aviso();

            System.out.println("Quanto deseja decrescentar?: ");
            String estoqueString = scanner.nextLine();
            if(estoqueString.isBlank()) return;
            else estoque = Integer.parseInt(estoqueString);

            if(!BDService.estoqueEhVerificado(estoque))
                System.out.println(ResultadoOperacao.mensagem(ResultadoOperacao.ESTOQUE_INVALIDO));
            else {
                String erro = LojaDAO.atualizarEstoque(produto.getIdProduto(), estoque * -1);
                if(erro == null) break;
                else System.out.println(erro);
            }
        }
    }

    public static void deletar() {
        Produto produto = verificarId();
        if(produto == null) return;

        mostrarDados(produto);
        System.out.print("Deseja deletar esse produto?(s/n): ");
        char escolha = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();

        if(escolha == 'S') {
            LojaDAO.deletar(produto.getIdProduto());
            System.out.println("\n\n(Produto deletado com sucesso)\n\n");
            espera();
        }
    }
}
