package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.BaseInterDAO;
import model.DAO.UsuarioDAO;
import model.VO.UsuarioVO;

public class UsuarioBO implements BaseInterBO<UsuarioVO> {
	
	static private BaseInterDAO<UsuarioVO> dao = new UsuarioDAO();
	
	@Override
	public void inserir(UsuarioVO entidade) throws SQLException {
		
		UsuarioDAO usuario = new UsuarioDAO();
		
		try {
			ResultSet resposta = usuario.buscarPorCpf(entidade);
			if (resposta.next()) {
				throw new Exception("Já existe um usuario com esse cpf");
			}
			else {
				dao.inserir(entidade);
			}
		}
		catch (Exception erro) {
		      System.err.println(erro);
		}
	}

	@Override
	public void editar(UsuarioVO entidade) throws SQLException {
		
		UsuarioDAO usuario = new UsuarioDAO();
		
		try {
			ResultSet resposta = usuario.buscarPorId(entidade);
			if (!resposta.next()) {
				throw new Exception("Não existe esse usuario.");
			}
			else {
				dao.editar(entidade);
			}
		}
		catch (Exception erro) {
		      System.err.println(erro);
		}
	}

	@Override
	public void remover(UsuarioVO entidade) throws SQLException {
		
		UsuarioDAO usuario = new UsuarioDAO();
		
		try {
			ResultSet resposta = usuario.buscarPorId(entidade);
			if (!resposta.next()) {
				throw new Exception("Não existe esse usuario.");
			}
			else {
				dao.remover(entidade);
			}
		}
		catch (Exception erro) {
		      System.err.println(erro);
		}
	}

	@Override
	public List<UsuarioVO> buscarTodos() throws SQLException {
		
		try {
			ResultSet resposta = dao.buscarTodos();
			if (!resposta.next()) {
				throw new Exception("Não existe esse usuario.");
			}
			else {
				
				List<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
				
				while(resposta.next()) {
					
					UsuarioVO userRecebe = new UsuarioVO();
					
					userRecebe.setId(resposta.getInt("id"));
					userRecebe.setCpf(resposta.getString("cpf"));
					usuarios.add(userRecebe);
				}
				
				return usuarios;
				
			}
		}
		catch (Exception erro) {
		      System.err.println(erro);
		      return null;
		}
	}
}
