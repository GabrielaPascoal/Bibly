package model.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.VO.AluguelDiscoVO;
import model.VO.AluguelLivroVO;
import model.VO.AluguelVO;

public class AluguelDAO extends BaseDAO {

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

    AluguelVO aluguelEncontrado = new AluguelVO();

    aluguelEncontrado.setId(resposta.getInt("id"));
    aluguelEncontrado.setValor(resposta.getDouble("valor"));
    aluguelEncontrado.setData(resposta.getDate("data").toLocalDate());

    AluguelLivroVO alugueisLivros = new AluguelLivroVO();
    AluguelDiscoVO alugueisDiscos = new AluguelDiscoVO();
    // ClienteVO clientes = new ClienteVO();

    alugueisLivros.setAluguel(aluguelEncontrado);
    alugueisDiscos.setAluguel(aluguelEncontrado);
    // clientes.setAluguel(aluguelEncontrado);

    List<AluguelLivroVO> alugueisLivrosEncontrados = AluguelLivroDAO.buscarPorAluguelId(alugueisLivros);
    List<AluguelDiscoVO> alugueisDiscosEncontrados = AluguelDiscoDAO.buscarPorAluguelId(alugueisDiscos);
    // List<ClienteVO> clientesEncontrados =
    // ClienteDAO.buscarPorAluguelId(clientes);

    aluguelEncontrado.setLivros(alugueisLivrosEncontrados);
    aluguelEncontrado.setDiscos(alugueisDiscosEncontrados);
    // aluguelEncontrado.setCliente(clienteEncontrados);

    return aluguelEncontrado;
  }

  public static List<AluguelVO> buscarTodos() throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis";

    Statement statement = connection.createStatement();
    ResultSet resposta = statement.executeQuery(query);

    List<AluguelVO> alugueisEncontrados = new ArrayList<AluguelVO>();

    while (resposta.next()) {

      AluguelVO aluguel = new AluguelVO();

      aluguel.setId(resposta.getInt("id"));
      aluguel.setValor(resposta.getDouble("valor"));
      aluguel.setData(resposta.getDate("data").toLocalDate());

      AluguelLivroVO alugueisLivros = new AluguelLivroVO();
      AluguelDiscoVO alugueisDiscos = new AluguelDiscoVO();
      // ClienteVO clientes = new ClienteVO();

      alugueisLivros.setAluguel(aluguel);
      alugueisDiscos.setAluguel(aluguel);
      // clientes.setAluguel(aluguel);

      List<AluguelLivroVO> alugueisLivrosEncontrados = AluguelLivroDAO.buscarPorAluguelId(alugueisLivros);
      List<AluguelDiscoVO> alugueisDiscosEncontrados = AluguelDiscoDAO.buscarPorAluguelId(alugueisDiscos);
      // List<ClienteVO> clientesEncontrados =
      // ClienteDAO.buscarPorAluguelId(clientes);

      aluguel.setLivros(alugueisLivrosEncontrados);
      aluguel.setDiscos(alugueisDiscosEncontrados);
      // aluguel.setCliente(clienteEncontrados);

      alugueisEncontrados.add(aluguel);
    }

    return alugueisEncontrados;
  }

  public static List<AluguelVO> buscarPorMes(Integer monthNumber) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM alugueis WHERE EXTRACT(MONTH FROM data) = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, monthNumber);

    ResultSet resposta = preparedStatement.executeQuery();

    List<AluguelVO> alugueisEncontrados = new ArrayList<AluguelVO>();

    while (resposta.next()) {

      AluguelVO aluguelEncontrado = new AluguelVO();

      aluguelEncontrado.setId(resposta.getInt("id"));
      aluguelEncontrado.setValor(resposta.getDouble("valor"));
      aluguelEncontrado.setData(resposta.getDate("data").toLocalDate());

      AluguelLivroVO alugueisLivros = new AluguelLivroVO();
      AluguelDiscoVO alugueisDiscos = new AluguelDiscoVO();
      // ClienteVO clientes = new ClienteVO();

      alugueisLivros.setAluguel(aluguelEncontrado);
      alugueisDiscos.setAluguel(aluguelEncontrado);
      // clientes.setAluguel(aluguelEncontrado);

      List<AluguelLivroVO> alugueisLivrosEncontrados = AluguelLivroDAO.buscarPorAluguelId(alugueisLivros);
      List<AluguelDiscoVO> alugueisDiscosEncontrados = AluguelDiscoDAO.buscarPorAluguelId(alugueisDiscos);
      // List<ClienteVO> clientesEncontrados =
      // ClienteDAO.buscarPorAluguelId(clientes);

      aluguelEncontrado.setLivros(alugueisLivrosEncontrados);
      aluguelEncontrado.setDiscos(alugueisDiscosEncontrados);
      // aluguelEncontrado.setCliente(clienteEncontrados);

      alugueisEncontrados.add(aluguelEncontrado);
    }

    return alugueisEncontrados;
  }

}
