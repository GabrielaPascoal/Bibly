package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.VO.AluguelLivroVO;
import model.VO.AluguelVO;

public class AluguelLivroDAO extends BaseDAO {

  public static void inserir(AluguelLivroVO aluguelLivro) throws SQLException {
    Connection connection = getConnection();

    String query = "INSERT INTO aluguel_livros (aluguel_id, livro_id, quantidade) VALUES(?, ?, ?)";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelLivro.getAluguel().getId());
    preparedStatement.setInt(2, aluguelLivro.getProduto().getId());
    preparedStatement.setDouble(3, aluguelLivro.getQuantidade());

    preparedStatement.execute();
  }

  public static List<AluguelLivroVO> buscarPorAluguelId(AluguelLivroVO aluguelLivro) throws SQLException {
    Connection connection = getConnection();

    String query = "SELECT * FROM aluguel_livros WHERE aluguel_id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelLivro.getAluguel().getId());

    ResultSet resposta = preparedStatement.executeQuery();

    List<AluguelLivroVO> alugueisLivrosEncontrados = new ArrayList<AluguelLivroVO>();

    while (resposta.next()) {
      AluguelLivroVO aluguelLivroEncontrado = new AluguelLivroVO();
      AluguelVO aluguel = new AluguelVO();
      // LivroVO livro = new LivroVO();

      aluguel.setId(resposta.getInt("aluguel_id"));
      // livro.setId(resposta.getInt("livro_id"));

      aluguel = AluguelDAO.buscarPorId(aluguel);
      // livro = LivroDAO.buscarPorId(livro);

      aluguelLivroEncontrado.setAluguel(aluguel);
      // aluguelLivroEncontrado.setLivro(livro);
      aluguelLivroEncontrado.setQuantidade(resposta.getInt("quantidade"));

      alugueisLivrosEncontrados.add(aluguelLivroEncontrado);
    }

    return alugueisLivrosEncontrados;
  }

  public static void remover(AluguelLivroVO aluguelLivro) throws SQLException {
    Connection connection = getConnection();

    String query = "DELETE FROM aluguel_livros WHERE aluguel_id=? AND livro_id=?";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, aluguelLivro.getAluguel().getId());
    preparedStatement.setInt(2, aluguelLivro.getProduto().getId());

    preparedStatement.execute();
  }
}
