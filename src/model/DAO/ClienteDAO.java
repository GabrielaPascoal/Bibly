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
		preparedStatement.setString(4, vo.getCelular());
		preparedStatement.execute();

	}

	public void remover(ClienteVO vo) throws SQLException {

		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = getConnection().prepareStatement(sqlDelete);
			preparedStatement.setLong(1, vo.getId());
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
				vo.setCelular(result.getString("celular"));

				clientes.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;

	}

	public void editar(ClienteVO vo) throws SQLException {
		Connection connection = getConnection();
		String query = "UPDATE clientes SET nome = ?, cpf = ?, endereco = ?, celular = ? WHERE id =?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, vo.getNome());
			preparedStatement.setString(2, vo.getCpf());
			preparedStatement.setString(3, vo.getEndereco());
			preparedStatement.setString(4, vo.getCelular());
			preparedStatement.setInt(5, vo.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// BUSCAR TODOS
	public ResultSet buscarTodos() {

		String sql = "SELECT * FROM clientes";
		Statement st;
		ResultSet resposta = null;

		try {

			Connection connection = getConnection();
			st = connection.createStatement();
			resposta = st.executeQuery(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resposta;
	}

	// BUSCAR POR ID
	public ResultSet buscarPorId(ClienteVO cliente) {

		String sql = "SELECT * FROM clientes WHERE id=?";
		PreparedStatement ptst;
		ResultSet resposta = null;
		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setInt(1, cliente.getId());
			resposta = ptst.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resposta;
	}

	// BUSCAR POR NOME
	public ResultSet buscarPorNome(ClienteVO cliente) {

		String sql = "SELECT * FROM clientes WHERE nome = ?";
		PreparedStatement ptst;
		ResultSet resposta = null;

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, cliente.getNome());
			resposta = ptst.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resposta;
	}

	// BUSCAR POR CPF
	public ResultSet buscarPorCpf(ClienteVO cliente) {

		String sql = "SELECT * FROM clientes WHERE cpf = ?";
		PreparedStatement ptst;
		ResultSet resposta = null;

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, cliente.getCpf());
			resposta = ptst.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resposta;

	}

	// BUSCAR POR ENDERECO
	public ResultSet buscarPorEndereco(ClienteVO cliente) {

		String sql = "SELECT * FROM clientes WHERE endereco = ?";
		PreparedStatement ptst;
		ResultSet resposta = null;

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, cliente.getEndereco());
			resposta = ptst.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resposta;

	}

}
