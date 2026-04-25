package model;

import java.util.Objects;

public class Produto {
    private int idProduto;
    private String nomeProduto;
    private double precoProduto;
    private int estoque;

    public Produto() {}

    public Produto(int idProduto, String nomeProduto, double precoProduto, int estoque) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.estoque = estoque;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", precoProduto=" + precoProduto +
                ", estoque=" + estoque +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return idProduto == produto.idProduto && Double.compare(precoProduto, produto.precoProduto) == 0 && estoque == produto.estoque && Objects.equals(nomeProduto, produto.nomeProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto, nomeProduto, precoProduto, estoque);
    }
}
