package service;

public enum ResultadoOperacao {
    SUCESSO(0),
    ERRO_NOME(-1),
    PRECO_INVALIDO(-2),
    ESTOQUE_INVALIDO(-3);

    private int erro;

    ResultadoOperacao(int erro) {
        this.erro = erro;
    }

    public int getErro() {
        return erro;
    }

    public static String mensagem(ResultadoOperacao resutadoOperacao) {
        switch (resutadoOperacao) {
            case SUCESSO -> {
                return "\n\n(Ação feita com sucesso)\n\n";
            }

            case ERRO_NOME -> {
                return "\n\n(nome inválido, não pode ser nulo)\n\n";
            }

            case PRECO_INVALIDO -> {
                return "\n\n(preço não pode ser menor que 0)\n\n";
            }

            case ESTOQUE_INVALIDO -> {
                return "\n\n(estoque não pode ser menor que 0)\n\n";
            }

            default -> {
                return "\n\n(algo aconteceu)\n\n";
            }
        }
    }
}
