package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.AluguelDAO;
import model.DAO.AluguelDiscoDAO;
import model.DAO.DiscoDAO;
import model.VO.AluguelDiscoVO;
import model.VO.AluguelVO;
import model.VO.DiscoVO;

public class AluguelDiscoBO {
  public static AluguelDiscoVO getAluguelDisco(ResultSet resposta) throws SQLException {
    AluguelDiscoVO aluguelDisco = new AluguelDiscoVO();
    AluguelVO aluguel = new AluguelVO();
    DiscoVO disco = new DiscoVO();

    aluguel.setId(resposta.getInt("aluguel_id"));
    disco.setId(resposta.getInt("disco_id"));

    aluguelDisco.setAluguel(aluguel);
    aluguelDisco.setProduto(disco);
    aluguelDisco.setQuantidade(resposta.getInt("quantidade"));

    return aluguelDisco;
  }

  public static void alugar(AluguelDiscoVO aluguel) throws SQLException {
    try {
      AluguelDiscoDAO aluguelDiscoDAO = new AluguelDiscoDAO();
      DiscoDAO discoDAO = new DiscoDAO();
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getAluguel().getId() <= 0) {
        throw new Exception("Defina o id de um aluguel valido.");
      }

      if (aluguel.getQuantidade() <= 0) {
        throw new Exception("Quantidade de discos não pode ser menor ou igual a 0.");
      }

      if (aluguel.getProduto().getId() <= 0) {
        throw new Exception("Defina o id de um disco valido.");
      }

      ResultSet respostaAluguel = aluguelDAO.buscarPorId(aluguel.getAluguel());

      ResultSet respostaDisco = discoDAO.buscarPorId(aluguel.getProduto());

      if (respostaAluguel.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      if (respostaDisco.next()) {
        throw new Exception("Disco nao existe.");
      }

      if (respostaDisco.getInt("quantidade") < aluguel.getQuantidade()) {
        throw new Exception("Quantidade insuficiente.");
      }

      DiscoVO disco = new DiscoVO();

      disco.setId(respostaDisco.getInt("id"));
      disco.setTitulo(respostaDisco.getString("titulo"));
      disco.setArtista(respostaDisco.getString("artista"));
      disco.setEstilo(respostaDisco.getString("estilo"));
      disco.setValor(respostaDisco.getDouble("valor"));
      disco.setQuantidade(respostaDisco.getInt("quantidade") - aluguel.getQuantidade());

      discoDAO.editar(disco);
      aluguelDiscoDAO.inserir(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public static void devolver(AluguelDiscoVO aluguel) throws SQLException {
    try {
      DiscoDAO discoDAO = new DiscoDAO();

      if (aluguel.getProduto().getId() <= 0) {
        throw new Exception("Defina o id de um disco valido.");
      }

      ResultSet resposta = discoDAO.buscarPorId(aluguel.getProduto());
      DiscoVO disco = new DiscoVO();

      disco.setId(resposta.getInt("id"));
      disco.setTitulo(resposta.getString("titulo"));
      disco.setArtista(resposta.getString("artista"));
      disco.setEstilo(resposta.getString("estilo"));
      disco.setValor(resposta.getDouble("valor"));
      disco.setQuantidade(resposta.getInt("quantidade") + aluguel.getQuantidade());

      discoDAO.editar(disco);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public static List<AluguelDiscoVO> buscarTodos() throws SQLException {
    try {
      AluguelDiscoDAO aluguelDiscoDAO = new AluguelDiscoDAO();

      ResultSet resposta = aluguelDiscoDAO.buscarTodos();

      List<AluguelDiscoVO> alugueis = new ArrayList<AluguelDiscoVO>();

      while (resposta.next()) {
        AluguelDiscoVO aluguel = getAluguelDisco(resposta);
        alugueis.add(aluguel);
      }

      return alugueis;
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public static List<AluguelDiscoVO> buscarPorAluguelId(AluguelDiscoVO aluguel) throws SQLException {
    try {
      AluguelDiscoDAO aluguelDiscoDAO = new AluguelDiscoDAO();
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getAluguel().getId() <= 0) {
        throw new Exception("Defina o id de um aluguel valido.");
      }

      ResultSet respostaAluguel = aluguelDAO.buscarPorId(aluguel.getAluguel());

      if (respostaAluguel.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      ResultSet resposta = aluguelDiscoDAO.buscarPorAluguelId(aluguel);

      List<AluguelDiscoVO> alugueis = new ArrayList<AluguelDiscoVO>();

      while (resposta.next()) {
        aluguel = getAluguelDisco(resposta);
        alugueis.add(aluguel);
      }

      return alugueis;
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public static void editar(AluguelDiscoVO aluguel) throws SQLException {
    try {
      AluguelDiscoDAO aluguelDiscoDAO = new AluguelDiscoDAO();
      DiscoDAO discoDAO = new DiscoDAO();
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getAluguel().getId() <= 0) {
        throw new Exception("Defina o id de um aluguel valido.");
      }

      if (aluguel.getProduto().getId() <= 0) {
        throw new Exception("Defina o id de um disco valido.");
      }

      ResultSet resposta = aluguelDiscoDAO.buscarPorAluguelId(aluguel);

      AluguelDiscoVO aluguelAntigo = new AluguelDiscoVO();

      while (resposta.next()) {
        if (resposta.getInt("disco_id") == aluguel.getProduto().getId()) {
          aluguelAntigo = getAluguelDisco(resposta);
        }
      }

      if (aluguelAntigo.getProduto().getId() <= 0) {
        throw new Exception("Esse aluguel nao possui o disco definido.");
      }

      if (aluguel.getQuantidade() <= 0) {
        aluguel.setQuantidade(aluguelAntigo.getQuantidade());
      }

      ResultSet respostaAluguel = aluguelDAO.buscarPorId(aluguel.getAluguel());
      ResultSet respostaDisco = discoDAO.buscarPorId(aluguel.getProduto());

      if (respostaAluguel.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      if (respostaDisco.next()) {
        throw new Exception("Disco nao existe.");
      }

      if (respostaDisco.getInt("quantidade") < aluguel.getQuantidade()) {
        throw new Exception("Quantidade insuficiente.");
      }

      devolver(aluguelAntigo);

      DiscoVO disco = new DiscoVO();

      disco.setId(respostaDisco.getInt("id"));
      disco.setTitulo(respostaDisco.getString("titulo"));
      disco.setArtista(respostaDisco.getString("artista"));
      disco.setEstilo(respostaDisco.getString("estilo"));
      disco.setValor(respostaDisco.getDouble("valor"));
      disco.setQuantidade(respostaDisco.getInt("quantidade") - aluguel.getQuantidade());

      discoDAO.editar(disco);
      aluguelDiscoDAO.editar(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public static void remover(AluguelDiscoVO aluguel) throws SQLException {
    try {
      AluguelDiscoDAO aluguelDiscoDAO = new AluguelDiscoDAO();

      if (aluguel.getAluguel().getId() <= 0) {
        throw new Exception("Defina o id de um aluguel valido.");
      }

      if (aluguel.getProduto().getId() <= 0) {
        throw new Exception("Defina o id de um disco valido.");
      }

      ResultSet resposta = aluguelDiscoDAO.buscarPorAluguelId(aluguel);

      boolean aluguelExiste = false;

      while (resposta.next()) {
        if (resposta.getInt("disco_id") == aluguel.getProduto().getId()) {
          aluguelExiste = true;
        }
      }

      if (!aluguelExiste) {
        throw new Exception("Esse aluguel nao possui o disco definido.");
      }

      devolver(aluguel);
      remover(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }
}