package bd;

public class Produtos {
    private int id_produto;
    private String nome_produto;
    private double preco_produto;

    public Produtos(int id_produto, String nome_produto, double preco_produto) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.preco_produto = preco_produto;
    }

    public int getId_produto() {
        return id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public double getPreco_produto() {
        return preco_produto;
    }
}
