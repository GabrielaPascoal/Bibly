package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseInterDAO<VO> {
  public void inserir(VO entidade) throws SQLException;

  public void editar(VO entidade) throws SQLException;

  public void remover(VO entidade) throws SQLException;

  public ResultSet buscarTodos() throws SQLException;

}
