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

import model.VO.AluguelDiscoVO;
import model.VO.AluguelLivroVO;
import model.VO.AluguelVO;
import model.VO.ClienteVO;

public class AluguelDAO extends BaseDAO {

  private static AluguelVO formatarResposta(ResultSet resposta) throws SQLException {
    AluguelVO aluguel = new AluguelVO();

    aluguel.setId(resposta.getInt("id"));
    aluguel.setValor(resposta.getDouble("valor"));
    aluguel.setData(resposta.getDate("data").toLocalDate());

    AluguelLivroVO alugueisLivros = new AluguelLivroVO();
    AluguelDiscoVO alugueisDiscos = new AluguelDiscoVO();
    ClienteVO cliente = new ClienteVO();

    alugueisLivros.setAluguel(aluguel);
    alugueisDiscos.setAluguel(aluguel);
    cliente.setId(resposta.getInt("cliente_id"));

    List<AluguelLivroVO> alugueisLivrosEncontrados = AluguelLivroDAO.buscarPorAluguelId(alugueisLivros);
    List<AluguelDiscoVO> alugueisDiscosEncontrados = AluguelDiscoDAO.buscarPorAluguelId(alugueisDiscos);
    ClienteVO clienteEncontrado = ClienteDAO.buscarPorId(cliente);

    aluguel.setLivros(alugueisLivrosEncontrados);
    aluguel.setDiscos(alugueisDiscosEncontrados);
    aluguel.setCliente(clienteEncontrado);

    return aluguel;
  }

  public static void inserir(AluguelVO aluguel) throws SQLException {
    Connection connection = getConnection();

    String query = "INSERT INTO alugueis (cliente_id, valor) VALUES(?, ?)";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguel.getCliente().getId());
    preparedStatement.setDouble(2, aluguel.getValor());

    preparedStatement.execute();
  }

  public static AluguelVO buscarPorId(AluguelVO aluguel) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis WHERE id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguel.getId());

    ResultSet resposta = preparedStatement.executeQuery();

    if (!resposta.next()) {
      return null;
    }

    aluguel = formatarResposta(resposta);

    return aluguel;
  }

  public static List<AluguelVO> buscarTodos() throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis";

    Statement statement = connection.createStatement();
    ResultSet resposta = statement.executeQuery(query);

    List<AluguelVO> alugueisEncontrados = new ArrayList<AluguelVO>();

    while (resposta.next()) {

      AluguelVO aluguel = new AluguelVO();

      aluguel = formatarResposta(resposta);

      alugueisEncontrados.add(aluguel);
    }

    return alugueisEncontrados;
  }

  public static List<AluguelVO> buscarPorMes(Integer Mes) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis WHERE EXTRACT(MONTH FROM data) = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, Mes);

    ResultSet resposta = preparedStatement.executeQuery();

    List<AluguelVO> alugueisEncontrados = new ArrayList<AluguelVO>();

    while (resposta.next()) {

      AluguelVO aluguelEncontrado = new AluguelVO();

      aluguelEncontrado = formatarResposta(resposta);

      alugueisEncontrados.add(aluguelEncontrado);
    }

    return alugueisEncontrados;
  }

  public static List<AluguelVO> buscarPorCliente(AluguelVO aluguel) throws SQLException {
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

  public static List<AluguelVO> buscarPorIntervaloDeDias(LocalDate dataMin, LocalDate dataMax) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis WHERE (? <= data AND data <= ?)";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setDate(1, Date.valueOf(dataMin));
    preparedStatement.setDate(2, Date.valueOf(dataMax));

    ResultSet resposta = preparedStatement.executeQuery();

    List<AluguelVO> alugueisEncontrados = new ArrayList<AluguelVO>();

    while (resposta.next()) {

      AluguelVO aluguelEncontrado = new AluguelVO();

      aluguelEncontrado = formatarResposta(resposta);

      alugueisEncontrados.add(aluguelEncontrado);
    }

    return alugueisEncontrados;
  }

  public static void editar(AluguelVO aluguel) {

    String sql = "Update alugueis SET cliente_id = ?, valor = ?, data = ? where id = ? ";
    PreparedStatement preparedStatement;

    try {

      Connection connection = getConnection();
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, aluguel.getCliente().getId());
      preparedStatement.setDouble(2, aluguel.getValor());
      preparedStatement.setDate(3, Date.valueOf(aluguel.getData()));
      preparedStatement.setInt(4, aluguel.getId());
      preparedStatement.executeUpdate();

    } catch (SQLException e) {

      e.printStackTrace();
    }
  }

  public static void remover(AluguelVO aluguel) throws SQLException {
    Connection connection = getConnection();

    String query = "DELETE FROM aluguel_discos WHERE id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguel.getId());

    preparedStatement.execute();
  }
}
