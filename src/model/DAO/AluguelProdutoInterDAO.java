package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AluguelProdutoInterDAO<VO> {
  public ResultSet buscarPorAluguelId(VO entidade) throws SQLException;

}
