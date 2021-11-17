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

	public ClienteVO buscarPorId(ClienteVO Cliente) throws SQLException {
		try {
			ClienteDAO ClienteDAO = new ClienteDAO();

			if (Cliente.getId() <= 0) {
				throw new Exception("Defina um id valido.");
			}

			ResultSet resposta = ClienteDAO.buscarPorId(Cliente);

			ClienteVO c = new ClienteVO();

			c.setId(resposta.getInt("id"));
			c.setNome(resposta.getString("nome"));
			c.setEndereco(resposta.getString("endereco"));
			c.setCpf(resposta.getString("cpf"));
			c.setCelular(resposta.getString("celular"));

			return c;
		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}
	}

	// BUSCAR TODOS
	@Override
	public List<ClienteVO> buscarTodos() throws SQLException {
		// TODO Auto-generated method stub
		try {
			ResultSet result = dao.buscarTodos();
			if (!result.next()) {
				throw new Exception("Erro.");
			} else {

				List<ClienteVO> clientes = new ArrayList<ClienteVO>();

				while (result.next()) {

					ClienteVO c = new ClienteVO();

					c.setId(result.getInt("id"));
					c.setNome(result.getString("nome"));
					c.setEndereco(result.getString("endereco"));
					c.setCpf(result.getString("cpf"));
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
}
