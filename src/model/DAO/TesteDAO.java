package model.DAO;

import java.sql.SQLException;
import java.util.List;

import model.VO.*;

public class TesteDAO {

  public static void main(String[] args) throws SQLException {
    List<AluguelVO> alugueis = AluguelDAO.buscarTodos();

    System.out.println(alugueis);
  }
}
