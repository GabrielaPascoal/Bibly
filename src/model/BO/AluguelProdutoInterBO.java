package model.BO;

import java.sql.SQLException;
import java.util.List;

public interface AluguelProdutoInterBO<VO> {
  public List<VO> buscarPorAluguelId(VO entidade) throws SQLException;

  public void devolver(VO entidade) throws SQLException;
}
