package model.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.VO.*;

public class TesteDAO {

  public static void main(String[] args) throws SQLException {
    AluguelVO aluguel = new AluguelVO();
    ClienteVO cliente = new ClienteVO();

    cliente.setId(1);
    aluguel.setCliente(cliente);

    List<AluguelVO> alugueis = AluguelDAO.buscarPorIntervaloDeDias(LocalDate.of(2021, 8, 10),
        LocalDate.of(2021, 8, 11));

    System.out.println(alugueis);
  }
}
