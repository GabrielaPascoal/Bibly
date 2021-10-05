package model.BO;

import java.sql.SQLException;
import java.util.List;

public interface BaseInterBO<VO> {
  public void inserir(VO entidade) throws SQLException;

  public void editar(VO entidade) throws SQLException;

  public void remover(VO entidade) throws SQLException;

  public List<VO> buscarTodos() throws SQLException;

}
