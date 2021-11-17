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
		List<DiscoVO> disco = new ArrayList<DiscoVO>();
		try {
			ResultSet result = dao.buscarTodos();
			if (!result.next()) {
				throw new Exception("Erro.");
			} else {

				do {
					DiscoVO d = new DiscoVO();

					d.setId(result.getInt("id"));
					d.setTitulo(result.getString("titulo"));
					d.setArtista(result.getString("artista"));
					d.setEstilo(result.getString("estilo"));
					;
					d.setValor(result.getDouble("valor"));
					d.setQuantidade(result.getInt("quantidade"));
					disco.add(d);
				} while (result.next());

				return disco;
			}

		} catch (Exception erro) {
			System.err.println(erro);
			return disco;
		}

	}

	// BUSCAR POR ARTISTA
	public List<DiscoVO> buscarPorArtista(DiscoVO vo) throws SQLException {

		DiscoDAO disco = new DiscoDAO();

		try {

			ResultSet result = disco.buscarPorArtista(vo);
			if (!result.next()) {
				throw new Exception("Esse artista não existe.");
			} else {

				List<DiscoVO> discos = new ArrayList<DiscoVO>();

				while (result.next()) {

					DiscoVO d = new DiscoVO();
					d.setId(result.getInt("id"));
					d.setTitulo(result.getString("titulo"));
					d.setArtista(result.getString("artista"));
					d.setEstilo(result.getString("estilo"));
					;
					d.setValor(result.getDouble("valor"));
					d.setQuantidade(result.getInt("quantidade"));
					discos.add(d);

				}

				return discos;
			}
		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}
	}

	// BUSCAR POR ESTILO
	public List<DiscoVO> buscarPorEstilo(DiscoVO vo) throws SQLException {

		DiscoDAO disco = new DiscoDAO();

		try {
			ResultSet result = disco.buscarPorEstilo(vo);
			if (!result.next()) {
				throw new Exception("Não existe nenhum disco cadastrado com esse estilo.");
			} else {

				List<DiscoVO> discos = new ArrayList<DiscoVO>();

				while (result.next()) {

					DiscoVO d = new DiscoVO();

					d.setId(result.getInt("id"));
					d.setTitulo(result.getString("titulo"));
					d.setArtista(result.getString("artista"));
					d.setEstilo(result.getString("estilo"));
					;
					d.setValor(result.getDouble("valor"));
					d.setQuantidade(result.getInt("quantidade"));
					discos.add(d);

				}

				return discos;
			}
		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}
	}

	// BUSCAR POR TITULO
	public List<DiscoVO> buscarPorTitulo(DiscoVO vo) throws SQLException {

		DiscoDAO disco = new DiscoDAO();

		try {
			ResultSet result = disco.buscarPorTitulo(vo);
			if (!result.next()) {
				throw new Exception("Não existe nenhum disco cadastrado com esse titulo.");
			} else {

				List<DiscoVO> discos = new ArrayList<DiscoVO>();

				while (result.next()) {

					DiscoVO d = new DiscoVO();

					d.setId(result.getInt("id"));
					d.setTitulo(result.getString("titulo"));
					d.setArtista(result.getString("artista"));
					d.setEstilo(result.getString("estilo"));
					;
					d.setValor(result.getDouble("valor"));
					d.setQuantidade(result.getInt("quantidade"));
					discos.add(d);

				}

				return discos;
			}
		} catch (Exception erro) {
			System.err.println(erro);
			return null;
		}
	}

	// BUSCAR POR ID
	public List<DiscoVO> buscarPorId(DiscoVO vo) throws SQLException {

		DiscoDAO disco = new DiscoDAO();

		try {
			ResultSet result = disco.buscarPorId(vo);
			if (!result.next()) {
				throw new Exception("Não existe nenhum disco cadastrado com esse id.");
			} else {

				List<DiscoVO> discos = new ArrayList<DiscoVO>();

				while (result.next()) {

					DiscoVO d = new DiscoVO();

					d.setId(result.getInt("id"));
					d.setTitulo(result.getString("titulo"));
					d.setArtista(result.getString("artista"));
					d.setEstilo(result.getString("estilo"));
					;
					d.setValor(result.getDouble("valor"));
					d.setQuantidade(result.getInt("quantidade"));
					discos.add(d);

				}

				return discos;
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
				throw new Exception("Cliente não existente");
			}

			DiscoDAO.remover(entidade);
		} catch (Exception erro) {
			System.err.println(erro);
		}
	}

}