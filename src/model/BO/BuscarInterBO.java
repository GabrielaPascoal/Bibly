package model.BO;

import java.sql.SQLException;

public interface BuscarInterBO<VO> {
  public VO buscarPorId(VO entidade) throws SQLException;
}
