package model.BO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.BaseInterDAO;
import model.DAO.ClienteDAO;
import model.VO.ClienteVO;

public class ClienteBO implements BaseInterBO<ClienteVO>{
	
	static private BaseInterDAO<ClienteVO> dao = new ClienteDAO();	

	
    // INSERIR
	@Override
	public void inserir(ClienteVO entidade) throws SQLException {
		// TODO Auto-generated method stub
		ClienteDAO cliente = new ClienteDAO();
		try {
			ResultSet result = cliente.buscarPorId(entidade);
			if(result.next()) {
				throw new Exception("Erro.");
			}
			else {
				dao.inserir(entidade);
			}
		}
		catch (Exception erro) {
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

		      ResultSet resposta = ClienteDAO.buscarPorId(entidade);

		      if (!resposta.next()) {
		        throw new Exception("Cliente não existente");
		      }

		      ClienteDAO.remover(entidade);
		    } catch (Exception erro) {
		      System.err.println(erro);
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
			}
			else {
				
				List<ClienteVO> clientes = new ArrayList<ClienteVO>();
				
				while(result.next()) {
					
					ClienteVO c = new ClienteVO();
					
					c.setId(result.getInt("id"));
					c.setCpf(result.getString("cpf"));
					clientes.add(c);
				}
				
				return clientes;
				
			}
		}
		catch (Exception erro) {
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
			}
			else {
				dao.editar(entidade);
			}
		}
		catch (Exception erro) {
		      System.err.println(erro);
		}
	}
		
	}



	 