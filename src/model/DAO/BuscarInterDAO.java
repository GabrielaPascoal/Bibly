package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BuscarInterDAO<VO> {
  public ResultSet buscarPorId(VO entidade) throws SQLException;
}
