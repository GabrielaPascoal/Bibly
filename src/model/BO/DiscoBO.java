package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.BaseInterDAO;
import model.DAO.DiscoDAO;
import model.VO.DiscoVO;

public class DiscoBO implements BaseInterBO<DiscoVO> {

	static private BaseInterDAO<DiscoVO> dao = new DiscoDAO();

	// INSERIR
	@Override
	public void inserir(DiscoVO entidade) throws SQLException {
		// TODO Auto-generated method stub
		DiscoDAO disco = new DiscoDAO();

		try {
			ResultSet result = disco.buscarPorId(entidade);
			if (result.next()) {
				throw new Exception("Erro.");
			} else {
				dao.inserir(entidade);
			}
		} catch (Exception erro) {
			System.err.println(erro);
		}

	}

	// EDITAR
	@Override
	public void editar(DiscoVO entidade) throws SQLException {
		// TODO Auto-generated method stub
		DiscoDAO disco = new DiscoDAO();

		try {
			ResultSet result = disco.buscarPorId(entidade);
			if (!result.next()) {
				throw new Exception("Erro.");
			} else {
				dao.editar(entidade);
			}
		} catch (Exception erro) {
			System.err.println(erro);
		}

	}

	// BUSCAR TODOS
	@Override
	public List<DiscoVO> buscarTodos() throws SQLException {
		// TODO Auto-generated method stub
		try {
			ResultSet result = dao.buscarTodos();
			if (!result.next()) {
				throw new Exception("Erro.");
			} else {

				List<DiscoVO> disco = new ArrayList<DiscoVO>();

				while (result.next()) {
					DiscoVO d = new DiscoVO();

					d.setId(result.getInt("id"));
					d.setTitulo(result.getString("titulo"));
					d.setArtista(result.getString("artista"));
					d.setEstilo(result.getString("estilo"));
					;
					d.setValor(result.getDouble("valor"));
					d.setQuantidade(result.getInt("quantidade"));
					disco.add(d);
				}

				return disco;
			}

		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}

	}

	// REMOVER
	@Override
	public void remover(DiscoVO entidade) throws SQLException {
		// TODO Auto-generated method stub
		try {
			DiscoDAO DiscoDAO = new DiscoDAO();

			if (entidade.getId() <= 0) {
				throw new Exception("Defina um id valido.");
			}

			ResultSet result = DiscoDAO.buscarPorId(entidade);

			if (!result.next()) {
				throw new Exception("Cliente n�o existente");
			}

			DiscoDAO.remover(entidade);
		} catch (Exception erro) {
			System.err.println(erro);
		}
	}

	public List<DiscoVO> buscarPorId(DiscoVO liv) throws SQLException {

		DiscoDAO disco = new DiscoDAO();

		try {
			ResultSet resposta = disco.buscarPorId(liv);
			if (!resposta.next()) {
				throw new Exception("N�o existe nenhum disco cadastrado com esse titulo.");
			} else {

				List<DiscoVO> discos = new ArrayList<DiscoVO>();

				while (resposta.next()) {

					DiscoVO discoRecebe = new DiscoVO();

					discoRecebe.setId(resposta.getInt("id"));
					discoRecebe.setTitulo(resposta.getString("titulo"));
					discoRecebe.setArtista(resposta.getString("artista"));
					discoRecebe.setEstilo(resposta.getString("estilo"));
					discoRecebe.setValor(resposta.getDouble("valor"));
					discoRecebe.setQuantidade(resposta.getInt("quantidade"));
					discos.add(discoRecebe);

				}

				return discos;
			}
		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}

	}

}