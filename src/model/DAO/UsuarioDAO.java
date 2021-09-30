package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import model.VO.UsuarioVO;

public class UsuarioDAO extends BaseDAO<UsuarioVO>   {
	
	
	// Inserir usuarios.
		public void inserir(UsuarioVO user) {
			
			String sql = "INSERT INTO usuarios (cpf,senha) VALUES (?,?)";		
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
		public void remover(UsuarioVO user) {
			
			String sql = "DELETE FROM usuarios WHERE id = ?";
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
			
			String sql = "UPDATE usuarios SET cpf = ?, senha = ? WHERE id = ? ";
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
		public ResultSet buscarTodos() {
			
			String sql = "SELECT * FROM usuarios";		
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
		
}
