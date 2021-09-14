package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.VO.DiscoVO;



public class DiscoDAO extends BaseDAO {
	
	// INSERIR
	public void inserir(DiscoVO vo) throws SQLException {
		
	Connection connection = getConnection();
	String query = "INSERT INTO discos (titulo, artista, estilo, valor, quantidade) VALUES(?, ?, ?, ?, ?)";
	PreparedStatement preparedStatement = connection.prepareStatement(query);
	
	preparedStatement.setString(1, vo.getTitulo());
	preparedStatement.setString(2, vo.getArtista());
	preparedStatement.setString(3, vo.getEstilo());
	preparedStatement.setDouble(4, vo.getValor());
	preparedStatement.setInt(5, vo.getQuantidade());
	preparedStatement.execute();
	
	}
	
	// DELETAR
    public void deletarPorId(DiscoVO vo) throws SQLException {
		
		String query = "DELETE FROM discos WHERE id = ?";
		PreparedStatement preparedStatement;
		
		try {
			
			Connection connection = getConnection();
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setInt(1, vo.getId());
			 preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
    
     // LISTAR
     public List<DiscoVO> listarDiscos() throws SQLException {
		
		Connection connection = getConnection();
		String query = "SELECT * FROM discos";
		List<DiscoVO> discos = new ArrayList <DiscoVO>();
		
		try {
		    Statement st = connection.createStatement();
		    ResultSet result = st.executeQuery(query);
		    while(result.next()) {
		    	
		    	DiscoVO vo = new DiscoVO();
		    	vo.setId(result.getInt("id"));
		    	vo.setTitulo(result.getString("titulo"));
		    	vo.setArtista(result.getString("artista"));
		    	vo.setEstilo(result.getString("estilo"));
		    	vo.setValor(result.getDouble("valor"));
		    	vo.setQuantidade(result.getInt("quantidade"));
		    	
		    	discos.add(vo);    	
			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return discos;
			
		
     }
     
	 // ALTERAR
     public void alterarDisco(DiscoVO vo) {
		
		String query = "UPDATE discos SET titulo = ?, artista = ?, estilo = ?, valor = ?, quantidade = ?, WHERE id = ? ";
		PreparedStatement preparedStatement;
		
		try {
			
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, vo.getTitulo());
			preparedStatement.setString(2, vo.getArtista());
			preparedStatement.setString(3, vo.getEstilo());
			preparedStatement.setDouble(4, vo.getValor());
			preparedStatement.setInt(5, vo.getQuantidade());
			preparedStatement.setInt(6, vo.getId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
     
     //BUSCAR POR TITULO
     public ResultSet buscarPorTitulo(DiscoVO vo) {
    	 
	String query = "SELECT * FROM discos WHERE titulo = ?";
	PreparedStatement preparedStatement;
	ResultSet result = null;
			
		try {
		preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setString(1, vo.getTitulo());
		result = preparedStatement.executeQuery();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;	
	
}
     
     // BUSCAR POR ARTISTA
     public ResultSet buscarPorArtista(DiscoVO vo) {
    	 
	String query = "SELECT * FROM discos WHERE artista = ?";
	PreparedStatement preparedStatement;
	ResultSet result = null;
			
		try {
		preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setString(1, vo.getArtista());
		result = preparedStatement.executeQuery();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;	
	
}
     
     // BUSCAR POR ESTILO
     public ResultSet buscarPorEstilo(DiscoVO vo) {
    	 
    		String query = "SELECT * FROM discos WHERE estilo = ?";
    		PreparedStatement preparedStatement;
    		ResultSet result = null;
    				
    			try {
    			preparedStatement = getConnection().prepareStatement(query);
    			preparedStatement.setString(1, vo.getEstilo());
    			result = preparedStatement.executeQuery();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return result;	
    		
    	}

 
}


