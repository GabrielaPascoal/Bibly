package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.VO.UsuarioVO;

public class UsuarioDAO extends BaseDAO  {
	
	
	// Inserir usuarios.
		public void inserir(UsuarioVO user) {
			
			String sql = "insert into usuarios (cpf,senha) values (?,?)";		
			PreparedStatement ptst;
			
			try {
				
				Connection connection = getConnection();
				ptst = connection.prepareStatement(sql);
				ptst.setString(1, user.getCpf());
				ptst.setString(2, user.getSenha());
				ptst.execute();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		// Remover usuarios cadastrados. 
		public void removerPorId(UsuarioVO user) {
			
			String sql = "delete from usuarios where id = ?";
			PreparedStatement ptst;
			
			try {
				
				Connection connection = getConnection();
				ptst = connection.prepareStatement(sql);
				ptst.setInt(1, user.getId());
				ptst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// Editar usuarios cadastrados.
		public void editar(UsuarioVO user) {
			
			String sql = "Update usuarios SET cpf = ?, senha = ? where id = ? ";
			PreparedStatement ptst;
			
			try {
				
				Connection connection = getConnection();
				ptst = connection.prepareStatement(sql);
				ptst.setString(1, user.getCpf());
				ptst.setString(2, user.getSenha());
				ptst.setInt(3, user.getId());
				ptst.executeUpdate();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
		}
		
		// Buscar todos os usuarios cadastrados.
		public List<UsuarioVO> buscar() {
			
			String sql = "select * from usuarios";		
			Statement st;
			
			List<UsuarioVO> livrosEncontrados = new ArrayList<UsuarioVO>();
			
			try {
				
				Connection connection = getConnection();
				st = connection.createStatement();
				ResultSet resposta = st.executeQuery(sql);
				
				while (resposta.next()) {
					
					UsuarioVO buscarUser = new UsuarioVO();
					
					buscarUser.setId(resposta.getInt("id"));
					buscarUser.setCpf(resposta.getString("cpf"));
					buscarUser.setSenha(resposta.getString("senha"));
					
					//adicionando a lista.
					livrosEncontrados.add(buscarUser);
					
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
			
			return livrosEncontrados;
		}
		
}
