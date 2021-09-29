package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO<VO> implements BaseInterDAO<VO> {
  private static Connection connection = null;

  public static Connection getConnection() throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/bibly";
    String user = "postgres";
    String senha = "postgres";

    return connection == null ? DriverManager.getConnection(url, user, senha) : connection;
  }
}