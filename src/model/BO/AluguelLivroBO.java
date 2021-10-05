package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.AluguelDAO;
import model.DAO.AluguelLivroDAO;
import model.DAO.LivroDAO;
import model.VO.AluguelLivroVO;
import model.VO.AluguelVO;
import model.VO.LivroVO;

public class AluguelLivroBO {
  public static AluguelLivroVO getAluguelLivro(ResultSet resposta) throws SQLException {
    AluguelLivroVO aluguelLivro = new AluguelLivroVO();
    AluguelVO aluguel = new AluguelVO();
    LivroVO livro = new LivroVO();

    aluguel.setId(resposta.getInt("aluguel_id"));
    livro.setId(resposta.getInt("livro_id"));

    aluguelLivro.setAluguel(aluguel);
    aluguelLivro.setProduto(livro);
    aluguelLivro.setQuantidade(resposta.getInt("quantidade"));

    return aluguelLivro;
  }

  public static void alugar(AluguelLivroVO aluguel) throws SQLException {
    try {
      AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
      LivroDAO livroDAO = new LivroDAO();
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getAluguel().getId() <= 0) {
        throw new Exception("Defina o id de um aluguel valido.");
      }

      if (aluguel.getQuantidade() <= 0) {
        throw new Exception("Quantidade de livros não pode ser menor ou igual a 0.");
      }

      if (aluguel.getProduto().getId() <= 0) {
        throw new Exception("Defina o id de um livro valido.");
      }

      ResultSet respostaAluguel = aluguelDAO.buscarPorId(aluguel.getAluguel());

      ResultSet respostaLivro = livroDAO.buscarPorId(aluguel.getProduto());

      if (respostaAluguel.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      if (respostaLivro.next()) {
        throw new Exception("Livro nao existe.");
      }

      if (respostaLivro.getInt("quantidade") < aluguel.getQuantidade()) {
        throw new Exception("Quantidade insuficiente.");
      }

      LivroVO livro = new LivroVO();

      livro.setId(respostaLivro.getInt("id"));
      livro.setTitulo(respostaLivro.getString("titulo"));
      livro.setAutor(respostaLivro.getString("autor"));
      livro.setEstilo(respostaLivro.getString("genero"));
      livro.setAno(respostaLivro.getInt("ano"));
      livro.setPagina(respostaLivro.getInt("paginas"));
      livro.setValor(respostaLivro.getDouble("valor"));
      livro.setQuantidade(respostaLivro.getInt("quantidade") - aluguel.getQuantidade());

      livroDAO.editar(livro);
      aluguelLivroDAO.inserir(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public static void devolver(AluguelLivroVO aluguel) throws SQLException {
    try {
      LivroDAO livroDAO = new LivroDAO();

      if (aluguel.getProduto().getId() <= 0) {
        throw new Exception("Defina o id de um livro valido.");
      }

      ResultSet resposta = livroDAO.buscarPorId(aluguel.getProduto());
      LivroVO livro = new LivroVO();

      livro.setId(resposta.getInt("id"));
      livro.setTitulo(resposta.getString("titulo"));
      livro.setAutor(resposta.getString("autor"));
      livro.setEstilo(resposta.getString("genero"));
      livro.setAno(resposta.getInt("ano"));
      livro.setPagina(resposta.getInt("paginas"));
      livro.setValor(resposta.getDouble("valor"));
      livro.setQuantidade(resposta.getInt("quantidade") + aluguel.getQuantidade());

      livroDAO.editar(livro);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public static List<AluguelLivroVO> buscarTodos() throws SQLException {
    try {
      AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();

      ResultSet resposta = aluguelLivroDAO.buscarTodos();

      List<AluguelLivroVO> alugueis = new ArrayList<AluguelLivroVO>();

      while (resposta.next()) {
        AluguelLivroVO aluguel = getAluguelLivro(resposta);
        alugueis.add(aluguel);
      }

      return alugueis;
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public static List<AluguelLivroVO> buscarPorAluguelId(AluguelLivroVO aluguel) throws SQLException {
    try {
      AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getAluguel().getId() <= 0) {
        throw new Exception("Defina o id de um aluguel valido.");
      }

      ResultSet respostaAluguel = aluguelDAO.buscarPorId(aluguel.getAluguel());

      if (respostaAluguel.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      ResultSet resposta = aluguelLivroDAO.buscarPorAluguelId(aluguel);

      List<AluguelLivroVO> alugueis = new ArrayList<AluguelLivroVO>();

      while (resposta.next()) {
        aluguel = getAluguelLivro(resposta);
        alugueis.add(aluguel);
      }

      return alugueis;
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public static void editar(AluguelLivroVO aluguel) throws SQLException {
    try {
      AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
      LivroDAO livroDAO = new LivroDAO();
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getAluguel().getId() <= 0) {
        throw new Exception("Defina o id de um aluguel valido.");
      }

      if (aluguel.getProduto().getId() <= 0) {
        throw new Exception("Defina o id de um livro valido.");
      }

      ResultSet resposta = aluguelLivroDAO.buscarPorAluguelId(aluguel);

      AluguelLivroVO aluguelAntigo = new AluguelLivroVO();

      while (resposta.next()) {
        if (resposta.getInt("livro_id") == aluguel.getProduto().getId()) {
          aluguelAntigo = getAluguelLivro(resposta);
        }
      }

      if (aluguelAntigo.getProduto().getId() <= 0) {
        throw new Exception("Esse aluguel nao possui o livro definido.");
      }

      if (aluguel.getProduto().getId() <= 0) {
        aluguel.setProduto(aluguelAntigo.getProduto());
      }

      ResultSet respostaAluguel = aluguelDAO.buscarPorId(aluguel.getAluguel());
      ResultSet respostaLivro = livroDAO.buscarPorId(aluguel.getProduto());

      if (respostaAluguel.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      if (respostaLivro.next()) {
        throw new Exception("Livro nao existe.");
      }

      if (respostaLivro.getInt("quantidade") < aluguel.getQuantidade()) {
        throw new Exception("Quantidade insuficiente.");
      }

      devolver(aluguelAntigo);

      LivroVO livro = new LivroVO();

      livro.setId(respostaLivro.getInt("id"));
      livro.setTitulo(respostaLivro.getString("titulo"));
      livro.setAutor(respostaLivro.getString("autor"));
      livro.setEstilo(respostaLivro.getString("genero"));
      livro.setAno(respostaLivro.getInt("ano"));
      livro.setPagina(respostaLivro.getInt("paginas"));
      livro.setValor(respostaLivro.getDouble("valor"));
      livro.setQuantidade(respostaLivro.getInt("quantidade") - aluguel.getQuantidade());

      livroDAO.editar(livro);
      aluguelLivroDAO.editar(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public static void remover(AluguelLivroVO aluguel) throws SQLException {
    try {
      AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
      if (aluguel.getAluguel().getId() <= 0) {
        throw new Exception("Defina o id de um aluguel valido.");
      }

      if (aluguel.getProduto().getId() <= 0) {
        throw new Exception("Defina o id de um livro valido.");
      }

      ResultSet resposta = aluguelLivroDAO.buscarPorAluguelId(aluguel);

      boolean aluguelExiste = false;

      while (resposta.next()) {
        if (resposta.getInt("livro_id") == aluguel.getProduto().getId()) {
          aluguelExiste = true;
        }
      }

      if (!aluguelExiste) {
        throw new Exception("Esse aluguel nao possui o livro definido.");
      }

      devolver(aluguel);
      remover(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }
}