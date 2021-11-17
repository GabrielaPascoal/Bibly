package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.BaseInterDAO;
import model.DAO.ClienteDAO;
import model.VO.ClienteVO;

public class ClienteBO implements BaseInterBO<ClienteVO> {

	static private BaseInterDAO<ClienteVO> dao = new ClienteDAO();

	// INSERIR
	@Override
	public void inserir(ClienteVO entidade) throws SQLException {
		// TODO Auto-generated method stub
		ClienteDAO cliente = new ClienteDAO();
		try {
			ResultSet result = cliente.buscarPorId(entidade);
			if (result.next()) {
				throw new Exception("Erro.");
			} else {
				dao.inserir(entidade);
			}
		} catch (Exception erro) {
			System.err.println(erro);
		}
	}

	// REMOVER
	@Override
	public void remover(ClienteVO entidade) throws SQLException {
		// TODO Auto-generated method stub
		try {
			ClienteDAO ClienteDAO = new ClienteDAO();

			if (entidade.getId() <= 0) {
				throw new Exception("Defina um id valido.");
			}

			ResultSet result = ClienteDAO.buscarPorId(entidade);

			if (!result.next()) {
				throw new Exception("Cliente n�o existente");
			}

			ClienteDAO.remover(entidade);
		} catch (Exception erro) {
			System.err.println(erro);
		}
	}

	// EDITAR
	@Override
	public void editar(ClienteVO entidade) throws SQLException {
		// TODO Auto-generated method stub
		ClienteDAO cliente = new ClienteDAO();

		try {
			ResultSet result = cliente.buscarPorId(entidade);
			if (!result.next()) {
				throw new Exception("Erro.");
			} else {
				dao.editar(entidade);
			}
		} catch (Exception erro) {
			System.err.println(erro);
		}
	}

	// BUSCAR POR NOME
	public List<ClienteVO> buscarPorNome(ClienteVO vo) throws SQLException {

		ClienteDAO cliente = new ClienteDAO();

		try {

			ResultSet result = cliente.buscarPorNome(vo);
			if (!result.next()) {
				throw new Exception("Esse cliente n�o existe.");
			} else {

				List<ClienteVO> clientes = new ArrayList<ClienteVO>();

				while (result.next()) {

					ClienteVO c = new ClienteVO();
					c.setId(result.getInt("id"));
					c.setNome(result.getString("nome"));
					c.setCpf(result.getString("cpf"));
					c.setEndereco(result.getString("endereco"));
					;
					c.setCelular(result.getString("celular"));
					clientes.add(c);

				}

				return clientes;
			}
		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}
	}

	// BUSCAR POR CPF
	public List<ClienteVO> buscarPorCpf(ClienteVO vo) throws SQLException {

		ClienteDAO cliente = new ClienteDAO();

		try {

			ResultSet result = cliente.buscarPorCpf(vo);
			if (!result.next()) {
				throw new Exception("Esse cpf n�o existe.");
			} else {

				List<ClienteVO> clientes = new ArrayList<ClienteVO>();

				do {
					ClienteVO c = new ClienteVO();
					c.setId(result.getInt("id"));
					c.setNome(result.getString("nome"));
					c.setCpf(result.getString("cpf"));
					c.setEndereco(result.getString("endereco"));
					;
					c.setCelular(result.getString("celular"));
					clientes.add(c);

				} while (result.next());

				return clientes;
			}
		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}
	}

	// BUSCAR POR ENDERECO
	public List<ClienteVO> buscarPorEndereco(ClienteVO vo) throws SQLException {

		ClienteDAO cliente = new ClienteDAO();

		try {

			ResultSet result = cliente.buscarPorEndereco(vo);
			if (!result.next()) {
				throw new Exception("Esse endereco n�o existe.");
			} else {

				List<ClienteVO> clientes = new ArrayList<ClienteVO>();

				while (result.next()) {

					ClienteVO c = new ClienteVO();
					c.setId(result.getInt("id"));
					c.setNome(result.getString("nome"));
					c.setCpf(result.getString("cpf"));
					c.setEndereco(result.getString("endereco"));
					;
					c.setCelular(result.getString("celular"));
					clientes.add(c);

				}

				return clientes;
			}
		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}
	}

	// BUSCAR POR ID
	public List<ClienteVO> buscarPorId(ClienteVO vo) throws SQLException {

		ClienteDAO cliente = new ClienteDAO();

		try {

			ResultSet result = cliente.buscarPorId(vo);
			if (!result.next()) {
				throw new Exception("Esse cliente n�o existe.");
			} else {

				List<ClienteVO> clientes = new ArrayList<ClienteVO>();

				do {

					ClienteVO c = new ClienteVO();
					c.setId(result.getInt("id"));
					c.setNome(result.getString("nome"));
					c.setCpf(result.getString("cpf"));
					c.setEndereco(result.getString("endereco"));
					;
					c.setCelular(result.getString("celular"));
					clientes.add(c);

				} while (result.next());

				return clientes;
			}
		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}
	}

	// BUSCAR TODOS
	public List<ClienteVO> buscarTodos() throws SQLException {
		List<ClienteVO> cliente = new ArrayList<ClienteVO>();
		try {
			ResultSet result = dao.buscarTodos();
			if (!result.next()) {
				throw new Exception("Erro.");
			} else {

				while (result.next()) {
					ClienteVO c = new ClienteVO();

					c.setId(result.getInt("id"));
					c.setNome(result.getString("nome"));
					c.setCpf(result.getString("cpf"));
					c.setEndereco(result.getString("endereco"));
					;
					c.setCelular(result.getString("celular"));
					cliente.add(c);

				}

				return cliente;
			}

		} catch (Exception erro) {
			System.err.println(erro);
			return cliente;
		}

	}

}
