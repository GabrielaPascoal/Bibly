package model.DAO;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.VO.AluguelVO;

public class AluguelDAO extends BaseDAO<AluguelVO> implements BuscarInterDAO<AluguelVO> {

  private static AluguelVO formatarResposta(ResultSet resposta) throws SQLException {
    AluguelVO aluguel = new AluguelVO();

    aluguel.setId(resposta.getInt("id"));
    aluguel.setValor(resposta.getDouble("valor"));
    aluguel.setData(resposta.getDate("data").toLocalDate());

    return aluguel;
  }

  public void inserir(AluguelVO aluguel) throws SQLException {
    Connection connection = getConnection();

    String query = "INSERT INTO alugueis (cliente_id, valor) VALUES(?, ?)";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguel.getCliente().getId());
    preparedStatement.setDouble(2, aluguel.getValor());

    preparedStatement.execute();
  }

  public ResultSet buscarPorId(AluguelVO aluguel) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis WHERE id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguel.getId());

    ResultSet resposta = preparedStatement.executeQuery();

    return resposta;
  }

  public ResultSet buscarTodos() throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis";

    Statement statement = connection.createStatement();
    ResultSet resposta = statement.executeQuery(query);

    return resposta;
  }

  public List<AluguelVO> buscarPorCliente(AluguelVO aluguel) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis WHERE cliente_id= ?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguel.getCliente().getId());

    ResultSet resposta = preparedStatement.executeQuery();

    List<AluguelVO> alugueisEncontrados = new ArrayList<AluguelVO>();

    while (resposta.next()) {

      AluguelVO aluguelEncontrado = new AluguelVO();

      aluguelEncontrado = formatarResposta(resposta);

      alugueisEncontrados.add(aluguelEncontrado);
    }

    return alugueisEncontrados;
  }

  public ResultSet buscarPorIntervaloDeDias(LocalDate dataMin, LocalDate dataMax) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis WHERE (? <= data AND data <= ?)";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setDate(1, Date.valueOf(dataMin));
    preparedStatement.setDate(2, Date.valueOf(dataMax));

    ResultSet resposta = preparedStatement.executeQuery();

    return resposta;
  }

  public List<AluguelVO> buscarPorClienteEIntervaloDeDias(LocalDate dataMin, LocalDate dataMax, AluguelVO aluguel)
      throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis WHERE (? <= data AND data <= ?) AND cliente_id = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setDate(1, Date.valueOf(dataMin));
    preparedStatement.setDate(2, Date.valueOf(dataMax));
    preparedStatement.setInt(3, aluguel.getCliente().getId());

    ResultSet resposta = preparedStatement.executeQuery();

    List<AluguelVO> alugueisEncontrados = new ArrayList<AluguelVO>();

    while (resposta.next()) {

      AluguelVO aluguelEncontrado = new AluguelVO();

      aluguelEncontrado = formatarResposta(resposta);

      alugueisEncontrados.add(aluguelEncontrado);
    }

    return alugueisEncontrados;
  }

  public ResultSet buscarPorDevolucao() throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis WHERE devolucao IS NULL";

    PreparedStatement preparedStatement = connection.prepareStatement(query);

    ResultSet resposta = preparedStatement.executeQuery();

    return resposta;
  }

  public void editar(AluguelVO aluguel) throws SQLException {

    String sql = "Update alugueis SET cliente_id = ?, valor = ?, data = ?, devolucao = ? where id = ? ";
    PreparedStatement preparedStatement;

    try {

      Connection connection = getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, aluguel.getCliente().getId());
      preparedStatement.setDouble(2, aluguel.getValor());
      preparedStatement.setDate(3, Date.valueOf(aluguel.getData()));
      preparedStatement.setDate(4, Date.valueOf(aluguel.getDevolucao()));
      preparedStatement.setInt(5, aluguel.getId());
      preparedStatement.executeUpdate();

    } catch (SQLException e) {

      e.printStackTrace();
    }
  }

  public void remover(AluguelVO aluguel) throws SQLException {
    Connection connection = getConnection();

    String query = "DELETE FROM aluguel_discos WHERE id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguel.getId());

    preparedStatement.execute();
  }
}
