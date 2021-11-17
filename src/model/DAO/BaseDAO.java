package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO<VO> implements BaseInterDAO<VO> {
  private static Connection connection = null;
  private static final String url = "jdbc:postgresql://localhost:5432/bibly";
  private static final String user = "postgres";
  private static final String senha = "postgres";

  public static Connection getConnection() throws SQLException {
    try {
      return connection == null ? DriverManager.getConnection(url, user, senha) : connection;
    } catch (SQLException erro) {
      erro.printStackTrace();
      return connection;
    }
  }

  public static void closeConnection() throws SQLException {
    if (connection != null) {
      connection.close();
    }
  }
}