package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.DAO.AluguelDAO;
import model.DAO.ClienteDAO;
import model.VO.AluguelDiscoVO;
import model.VO.AluguelLivroVO;
import model.VO.AluguelVO;

public class AluguelBO extends BaseBO<AluguelVO> implements BuscarInterBO<AluguelVO> {
  public AluguelVO getAluguel(ResultSet resposta) throws SQLException {
    AluguelVO aluguel = new AluguelVO();
    AluguelLivroBO aluguelLivroBO = new AluguelLivroBO();
    AluguelDiscoBO aluguelDiscoBO = new AluguelDiscoBO();

    AluguelLivroVO livro = new AluguelLivroVO();
    AluguelDiscoVO disco = new AluguelDiscoVO();

    aluguel.setId(resposta.getInt("id"));
    aluguel.setValor(resposta.getDouble("valor"));
    aluguel.setData(resposta.getDate("data").toLocalDate());

    livro.setAluguel(aluguel);

    List<AluguelLivroVO> livros = aluguelLivroBO.buscarPorAluguelId(livro);
    List<AluguelDiscoVO> discos = aluguelDiscoBO.buscarPorAluguelId(disco);

    aluguel.setLivros(livros);
    aluguel.setDiscos(discos);
    // aluguel.setCliente();

    return aluguel;
  }

  public void inserir(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();
      ClienteDAO clienteDAO = new ClienteDAO();

      if (aluguel.getCliente().getId() <= 0) {
        throw new Exception("Defina o id de um cliente valido.");
      }

      if (aluguel.getValor() < 0) {
        throw new Exception("Valor nao pode ser menor que 0.");
      }

      ResultSet respostaCliente = clienteDAO.buscarPorId(aluguel.getCliente());

      if (respostaCliente.next()) {
        throw new Exception("Cliente nao existe.");
      }

      aluguelDAO.inserir(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public AluguelVO buscarPorId(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getId() <= 0) {
        throw new Exception("Defina um id valido.");
      }

      ResultSet resposta = aluguelDAO.buscarPorId(aluguel);

      if (resposta.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      return getAluguel(resposta);
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public List<AluguelVO> buscarTodos() throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();
      ResultSet resposta = aluguelDAO.buscarTodos();

      List<AluguelVO> alugueis = new ArrayList<AluguelVO>();

      while (resposta.next()) {
        AluguelVO aluguel = getAluguel(resposta);
        alugueis.add(aluguel);
      }

      return alugueis;
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public List<AluguelVO> buscarPorMes(Integer mes) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (mes < 1 && mes > 12) {
        throw new Exception("Escolha um mes de 1 a 12.");
      }

      return aluguelDAO.buscarPorMes(mes);
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public List<AluguelVO> buscarPorCliente(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getCliente().getId() <= 0) {
        throw new Exception("Defina o id de um cliente valido.");
      }

      return aluguelDAO.buscarPorCliente(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public List<AluguelVO> buscarPorIntervaloDeDias(LocalDate dataMin, LocalDate dataMax) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (dataMin == null && dataMax == null) {
        throw new Exception("Defina uma data valida.");
      }

      return aluguelDAO.buscarPorIntervaloDeDias(dataMin, dataMax);
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public void editar(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();
      ClienteDAO clienteDAO = new ClienteDAO();

      if (aluguel.getId() <= 0) {
        throw new Exception("Defina um id valido.");
      }

      ResultSet resposta = aluguelDAO.buscarPorId(aluguel);

      if (!resposta.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      AluguelVO aluguelAntigo = getAluguel(resposta);

      if (aluguel.getCliente().getId() <= 0) {
        aluguel.setCliente(aluguelAntigo.getCliente());
      }

      if (aluguel.getValor() < 0) {
        aluguel.setValor(aluguelAntigo.getValor());
      }

      ResultSet respostaCliente = clienteDAO.buscarPorId(aluguel.getCliente());

      if (respostaCliente.next()) {
        throw new Exception("Cliente nao existe.");
      }

      aluguelDAO.editar(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public void remover(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getId() <= 0) {
        throw new Exception("Defina um id valido.");
      }

      ResultSet resposta = aluguelDAO.buscarPorId(aluguel);

      if (!resposta.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      aluguelDAO.remover(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }
}
