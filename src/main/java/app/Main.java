package app;

import bd.LojaDAO;

public class Main {
    public static void main(String[] args) {
        LojaDAO lojaDAO = new LojaDAO();


//        lojaDAO.inserir("Pão", 3.45);
//        lojaDAO.inserir("Margarina", 5.00);

//        lojaDAO.atualizarNomeProduto(1, "Coxinha");
//        lojaDAO.atualizarPrecoProduto(2, 10);

//        lojaDAO.deletar(2);

        lojaDAO.listar().forEach(produto -> {
            System.out.print(produto.getId_produto() + " | ");
            System.out.print(produto.getNome_produto() + " | ");
            System.out.println(produto.getPreco_produto());
        });
    }
}
