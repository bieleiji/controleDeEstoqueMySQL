package service;

import bd.LojaDAO;

public class BDService {

    public static boolean nomeEhVerificado(String nome) {
        return !nome.isBlank();
    }

    public static boolean precoEhVerificado(double preco) {
        return preco >= 0;
    }

    public static boolean estoqueEhVerificado(int estoque) {
        return estoque >= 0;
    }

    public static ResultadoOperacao cadastrar(String nome, double preco, int estoque) {
        boolean nomeVerificado = nomeEhVerificado(nome);
        boolean precoVerificado = precoEhVerificado(preco);
        boolean estoqueVerificado = estoqueEhVerificado(estoque);

             if(!nomeVerificado)    return ResultadoOperacao.ERRO_NOME;
        else if(!precoVerificado)   return ResultadoOperacao.PRECO_INVALIDO;
        else if(!estoqueVerificado) return ResultadoOperacao.ESTOQUE_INVALIDO;
        else {
            LojaDAO.inserir(nome,preco,estoque);
            return ResultadoOperacao.SUCESSO;
        }
    }
}
