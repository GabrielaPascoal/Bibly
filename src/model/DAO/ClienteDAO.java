package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.VO.ClienteVO;

public class ClienteDAO extends BaseDAO<ClienteVO> implements BuscarInterDAO<ClienteVO> {

	public void inserir(ClienteVO vo) throws SQLException {
		Connection connection = getConnection();
		String query = "INSERT INTO clientes (nome, cpf, endereco, celular) VALUES(?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, vo.getNome());
		preparedStatement.setString(2, vo.getCpf());
		preparedStatement.setString(3, vo.getEndereco());
		preparedStatement.setInt(4, vo.getCelular());
		preparedStatement.execute();

	}

	// n�o est� deletando
	public void remover(ClienteVO vo) throws SQLException {

		Connection connection = getConnection();
		String query = "DELETE FROM clientes WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, vo.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<ClienteVO> listarClientes() throws SQLException {

		Connection connection = getConnection();
		String query = "SELECT * FROM clientes";
		List<ClienteVO> clientes = new ArrayList<ClienteVO>();

		try {
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery(query);
			while (result.next()) {
				ClienteVO vo = new ClienteVO();
				vo.setId(result.getInt("id"));
				vo.setNome(result.getString("nome"));
				vo.setCpf(result.getString("cpf"));
				vo.setEndereco(result.getString("endereco"));

				clientes.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;

	}

	public void editar(ClienteVO vo) throws SQLException {
		Connection connection = getConnection();
		String query = "UPDATE clientes SET nome = ?, cpf = ?, endereco = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, vo.getNome());
			preparedStatement.setString(2, vo.getCpf());
			preparedStatement.setString(1, vo.getEndereco());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ResultSet buscarPorId(ClienteVO vo) throws SQLException {
		String query = "SELECT * FROM clientes WHERE id = ?";
		PreparedStatement preparedStatement;
		ResultSet result = null;

		try {
			preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setInt(1, vo.getId());
			result = preparedStatement.executeQuery();

			if (!result.next()) {
				return null;
			}

			vo.setId(result.getInt("id"));
			vo.setNome(result.getString("nome"));
			vo.setCpf(result.getString("cpf"));
			vo.setEndereco(result.getString("endereco"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public static List<ClienteVO> buscarPorNome(ClienteVO vo) throws SQLException {
		String query = "SELECT * FROM clientes WHERE nome = ?";
		PreparedStatement preparedStatement;
		List<ClienteVO> clientes = new ArrayList<ClienteVO>();
		ResultSet result = null;

		try {
			preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setString(1, vo.getNome());
			result = preparedStatement.executeQuery();
			while (result.next()) {
				vo.setId(result.getInt("id"));
				vo.setNome(result.getString("nome"));
				vo.setCpf(result.getString("cpf"));
				vo.setEndereco(result.getString("endereco"));

				clientes.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;

	}

	public static ClienteVO buscarPorCpf(ClienteVO vo) throws SQLException {
		String query = "SELECT * FROM clientes WHERE cpf = ?";
		PreparedStatement preparedStatement;
		ResultSet result = null;

		try {
			preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setString(1, vo.getCpf());
			result = preparedStatement.executeQuery();

			if (!result.next()) {
				return null;
			}

			vo.setId(result.getInt("id"));
			vo.setNome(result.getString("nome"));
			vo.setCpf(result.getString("cpf"));
			vo.setEndereco(result.getString("endereco"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}

	public static List<ClienteVO> buscarPorEndereco(ClienteVO vo) throws SQLException {
		String query = "SELECT * FROM clientes WHERE endereco = ?";
		PreparedStatement preparedStatement;
		List<ClienteVO> clientes = new ArrayList<ClienteVO>();
		ResultSet result = null;

		try {
			preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setString(1, vo.getEndereco());
			result = preparedStatement.executeQuery();
			while (result.next()) {
				vo.setId(result.getInt("id"));
				vo.setNome(result.getString("nome"));
				vo.setCpf(result.getString("cpf"));
				vo.setEndereco(result.getString("endereco"));

				clientes.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;

	}
	// BUSCAR TODOS
    public ResultSet buscarTodos() throws SQLException {
            Connection connection = getConnection();

            String query = "SELECT * FROM clientes";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            return result;
        }
}
