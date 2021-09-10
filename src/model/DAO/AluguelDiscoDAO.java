package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.VO.AluguelDiscoVO;
import model.VO.AluguelVO;

public class AluguelDiscoDAO extends BaseDAO {

  public static void inserir(AluguelDiscoVO aluguelDisco) throws SQLException {
    Connection connection = getConnection();

    String query = "INSERT INTO aluguel_discos (aluguel_id, disco_id, quantidade) VALUES(?, ?, ?)";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelDisco.getAluguel().getId());
    preparedStatement.setInt(2, aluguelDisco.getProduto().getId());
    preparedStatement.setDouble(3, aluguelDisco.getQuantidade());

    preparedStatement.execute();
  }

  public static List<AluguelDiscoVO> buscarPorAluguelId(AluguelDiscoVO aluguelDisco) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM aluguel_discos WHERE aluguel_id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelDisco.getAluguel().getId());

    ResultSet resposta = preparedStatement.executeQuery();

    List<AluguelDiscoVO> alugueisDiscosEncontrados = new ArrayList<AluguelDiscoVO>();

    while (resposta.next()) {
      AluguelDiscoVO aluguelDiscoEncontrado = new AluguelDiscoVO();
      AluguelVO aluguel = new AluguelVO();
      // DiscoVO disco = new DiscoVO();

      aluguel.setId(resposta.getInt("aluguel_id"));
      // disco.setId(resposta.getInt("disco_id"));

      aluguelDiscoEncontrado.setAluguel(aluguel);
      // aluguelDiscoEncontrado.setDisco(disco);
      aluguelDiscoEncontrado.setQuantidade(resposta.getInt("quantidade"));

      alugueisDiscosEncontrados.add(aluguelDiscoEncontrado);
    }

    return alugueisDiscosEncontrados;
  }

  public static void remover(AluguelDiscoVO aluguelDisco) throws SQLException {
    Connection connection = getConnection();

    String query = "DELETE FROM aluguel_discos WHERE aluguel_id=? AND disco_id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelDisco.getAluguel().getId());
    preparedStatement.setInt(2, aluguelDisco.getProduto().getId());

    preparedStatement.execute();
  }
}
