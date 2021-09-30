package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.AluguelDiscoVO;

public class AluguelDiscoDAO extends BaseDAO<AluguelDiscoVO> implements AluguelProdutoInterDAO<AluguelDiscoVO> {

  public void inserir(AluguelDiscoVO aluguelDisco) throws SQLException {
    Connection connection = getConnection();

    String query = "INSERT INTO aluguel_discos (aluguel_id, disco_id, quantidade) VALUES(?, ?, ?)";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelDisco.getAluguel().getId());
    preparedStatement.setInt(2, aluguelDisco.getProduto().getId());
    preparedStatement.setDouble(3, aluguelDisco.getQuantidade());

    preparedStatement.execute();
  }

  public ResultSet buscarPorAluguelId(AluguelDiscoVO aluguelDisco) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM aluguel_discos WHERE aluguel_id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelDisco.getAluguel().getId());

    ResultSet resposta = preparedStatement.executeQuery();

    return resposta;
  }

  public ResultSet buscarTodos() throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM aluguel_discos";

    Statement statement = connection.createStatement();
    ResultSet resposta = statement.executeQuery(query);

    return resposta;
  }

  public void editar(AluguelDiscoVO aluguelDisco) throws SQLException {

    String sql = "Update aluguel_discos SET quantidade = ? where aluguel_id = ? AND disco_id=? ";
    PreparedStatement preparedStatement;

    try {

      Connection connection = getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, aluguelDisco.getQuantidade());
      preparedStatement.setInt(2, aluguelDisco.getAluguel().getId());
      preparedStatement.setInt(3, aluguelDisco.getProduto().getId());
      preparedStatement.executeUpdate();

    } catch (SQLException e) {

      e.printStackTrace();
    }
  }

  public void remover(AluguelDiscoVO aluguelDisco) throws SQLException {
    Connection connection = getConnection();

    String query = "DELETE FROM aluguel_discos WHERE aluguel_id=? AND disco_id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelDisco.getAluguel().getId());
    preparedStatement.setInt(2, aluguelDisco.getProduto().getId());

    preparedStatement.execute();
  }
}
