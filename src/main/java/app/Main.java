package app;

import bd.LojaDAO;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        LojaDAO lojaDAO = new LojaDAO();


//        lojaDAO.inserir("Pão", 3.45);
//        lojaDAO.inserir("Margarina", 5.00);

//        lojaDAO.atualizarNomeProduto(1, "Coxinha");
        lojaDAO.atualizarPrecoProduto(2, -3);

//        lojaDAO.deletar(2);

//        lojaDAO.listar().forEach(System.out::println);
//        });

//        System.out.println(lojaDAO.buscaPorId(1));

//        lojaDAO.buscarPorNome("a").forEach(System.out::println);
    }
}
