package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.VO.AluguelLivroVO;

public class AluguelLivroDAO extends BaseDAO<AluguelLivroVO> implements AluguelProdutoInterDAO<AluguelLivroVO> {

  public void inserir(AluguelLivroVO aluguelLivro) throws SQLException {
    Connection connection = getConnection();

    String query = "INSERT INTO aluguel_livros (aluguel_id, livro_id, quantidade) VALUES(?, ?, ?)";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelLivro.getAluguel().getId());
    preparedStatement.setInt(2, aluguelLivro.getProduto().getId());
    preparedStatement.setDouble(3, aluguelLivro.getQuantidade());

    preparedStatement.execute();
  }

  public ResultSet buscarPorAluguelId(AluguelLivroVO aluguelLivro) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM aluguel_livros WHERE aluguel_id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelLivro.getAluguel().getId());

    ResultSet resposta = preparedStatement.executeQuery();

    return resposta;
  }

  public ResultSet buscarTodos() throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM aluguel_livros";

    Statement statement = connection.createStatement();
    ResultSet resposta = statement.executeQuery(query);

    return resposta;
  }

  public void editar(AluguelLivroVO aluguelLivro) throws SQLException {

    String sql = "Update aluguel_livros SET quantidade = ? where aluguel_id = ? AND livro_id=? ";
    PreparedStatement preparedStatement;

    try {

      Connection connection = getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, aluguelLivro.getQuantidade());
      preparedStatement.setInt(2, aluguelLivro.getAluguel().getId());
      preparedStatement.setInt(3, aluguelLivro.getProduto().getId());
      preparedStatement.executeUpdate();

    } catch (SQLException e) {

      e.printStackTrace();
    }
  }

  public void remover(AluguelLivroVO aluguelLivro) throws SQLException {
    Connection connection = getConnection();

    String query = "DELETE FROM aluguel_livros WHERE aluguel_id=? AND livro_id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelLivro.getAluguel().getId());
    preparedStatement.setInt(2, aluguelLivro.getProduto().getId());

    preparedStatement.execute();
  }
}
