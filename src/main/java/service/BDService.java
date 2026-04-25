package service;

import bd.LojaDAO;

public class BDService {

    public static ResutadoOperacao cadastrar(String nome, double preco, int estoque) {
        if(nome.isBlank()) return ResutadoOperacao.ERRO_NOME;
        else if(preco < 0) return ResutadoOperacao.PRECO_INVALIDO;
        else if(estoque < 0) return ResutadoOperacao.ESTOQUE_INVALIDO;
        else {
            LojaDAO.inserir(nome,preco,estoque);
            return ResutadoOperacao.SUCESSO;
        }
    }
}
