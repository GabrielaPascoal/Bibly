package model.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.VO.*;

public class TesteDAO {

  public static void main(String[] args) throws SQLException {
    AluguelVO aluguel = new AluguelVO();
    AluguelLivroVO aluguelLivro = new AluguelLivroVO();
    AluguelDiscoVO aluguelDisco = new AluguelDiscoVO();
    LivroVO livro = new LivroVO();
    DiscoVO disco = new DiscoVO();

    aluguel.setId(3);
    livro.setId(3);
    disco.setId(2);
    aluguelLivro.setAluguel(aluguel);
    aluguelLivro.setProduto(livro);
    aluguelLivro.setQuantidade(3);
    aluguelDisco.setAluguel(aluguel);
    aluguelDisco.setProduto(disco);
    aluguelDisco.setQuantidade(5);
    aluguel.setValor(49.95);

    AluguelLivroDAO.editar(aluguelLivro);
    AluguelDiscoDAO.editar(aluguelDisco);
  }
}
