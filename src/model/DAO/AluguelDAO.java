package model.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    // ClienteVO cliente = ClienteDAO.buscarPorId(resposta.getInt("cliente_id"));
    // LivroVO[] livros =AluguelLivroDAO.buscarPorAluguelId(resposta.getInt("id"));
    // DiscoVO[] discos =AluguelDiscoDAO.buscarPorAluguelId(resposta.getInt("id"));

    aluguelEncontrado.setId(resposta.getInt("id"));
    aluguelEncontrado.setValor(resposta.getDouble("valor"));
    aluguelEncontrado.setData(resposta.getDate("data").toLocalDate());
    // aluguelEncontrado.setCliente(cliente);
    // aluguelEncontrado.setLivros(livros);
    // aluguelEncontrado.setDiscos(discos);

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

      // ClienteVO cliente = ClienteDAO.buscarPorId(resposta.getInt("cliente_id"));
      // LivroVO[] livros =AluguelLivroDAO.buscarPorAluguelId(resposta.getInt("id"));
      // DiscoVO[] discos =AluguelDiscoDAO.buscarPorAluguelId(resposta.getInt("id"));

      aluguel.setId(resposta.getInt("id"));
      aluguel.setValor(resposta.getDouble("valor"));
      aluguel.setData(resposta.getDate("data").toLocalDate());
      // aluguel.setCliente(cliente);
      // aluguel.setLivros(livros);
      // aluguel.setDiscos(discos);

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

      AluguelVO aluguelEcontrado = new AluguelVO();

      // ClienteVO cliente = ClienteDAO.buscarPorId(resposta.getInt("cliente_id"));
      // LivroVO[] livros =AluguelLivroDAO.buscarPorAluguelId(resposta.getInt("id"));
      // DiscoVO[] discos =AluguelDiscoDAO.buscarPorAluguelId(resposta.getInt("id"));

      aluguelEcontrado.setId(resposta.getInt("id"));
      aluguelEcontrado.setValor(resposta.getDouble("valor"));
      aluguelEcontrado.setData(resposta.getDate("data").toLocalDate());
      // aluguelEcontrado.setCliente(cliente);
      // aluguelEcontrado.setLivros(livros);
      // aluguelEcontrado.setDiscos(discos);

      alugueisEncontrados.add(aluguelEcontrado);
    }

    return alugueisEncontrados;
  }

}
