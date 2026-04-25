package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LojaDAO {

    public void inserir(String nome, Double preco) {
        String sql = """
                INSERT INTO produtos (nome_produto, preco_produto) VALUES (?,?);
        """;

        try(Connection connection = Conexao.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,nome);
            preparedStatement.setDouble(2, preco);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produtos> listar() {
        List<Produtos> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try(Connection connection = Conexao.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                produtos.add(
                        new Produtos(
                                resultSet.getInt("id_produto"),
                                resultSet.getString("nome_produto"),
                                resultSet.getDouble("preco_produto")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produtos;
    }

    public void atualizarNomeProduto(int id, String nome) {
        String sql = "UPDATE produtos SET nome_produto = ? WHERE id_produto = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarPrecoProduto(int id, double preco) {
        String sql = "UPDATE produtos SET preco_produto = ? WHERE id_produto = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, preco);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id_produto = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
