package bd;

import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LojaDAO {

    public static void inserir(String nome, Double preco, int estoque) {
        String sql = """
                INSERT INTO produtos (nome, preco, estoque) VALUES (?,?,?);
        """;

        try(Connection connection = Conexao.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,nome);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setInt(3, estoque);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try(Connection connection = Conexao.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                produtos.add(
                        new Produto(
                                resultSet.getInt("id_produto"),
                                resultSet.getString("nome"),
                                resultSet.getDouble("preco"),
                                resultSet.getInt("estoque")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produtos;
    }

    public static Produto buscaPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id_produto = ?";
        Produto produto = null;

        try (Connection connection = Conexao.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                produto = new Produto(
                        resultSet.getInt("id_produto"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("preco"),
                        resultSet.getInt("estoque")
                );
            }

            return produto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Produto> buscarPorNome(String nome) {
        String sql = "SELECT * FROM produtos WHERE LOWER(nome) LIKE LOWER(?)";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + nome + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                produtos.add(
                        new Produto(
                                resultSet.getInt("id_produto"),
                                resultSet.getString("nome"),
                                resultSet.getDouble("preco"),
                                resultSet.getInt("estoque")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produtos;
    }




    public static void atualizarNome(int id, String nome) {
        String sql = "UPDATE produtos SET nome = ? WHERE id_produto = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void atualizarPreco(int id, double preco) {
        String sql = "UPDATE produtos SET preco = ? WHERE id_produto = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, preco);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String atualizarEstoque(int id, int estoque) {
        String saida = "SELECT * FROM produtos WHERE id_produto = ?";
        String entrada = "UPDATE produtos SET estoque = estoque + ? WHERE id_produto = ?";

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmtSaida = connection.prepareStatement(saida)) {

            stmtSaida.setInt(1, id);
            ResultSet resultSet = stmtSaida.executeQuery();

            if(resultSet.next()) {
                if(resultSet.getInt("estoque") + estoque < 0) {
                    return "\n\n(ação não pode ser feita, pois estoque será menor que 0)\n\n";
                }
            }
            PreparedStatement stmtEntrada = connection.prepareStatement(entrada);
            stmtEntrada.setInt(1, estoque);
            stmtEntrada.setInt(2, id);
            stmtEntrada.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void deletar(int id) {
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
