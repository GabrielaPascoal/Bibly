package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.BaseInterDAO;
import model.DAO.LivroDAO;
import model.VO.LivroVO;


public class LivroBO implements BaseInterBO<LivroVO> {

	static private BaseInterDAO<LivroVO> dao = new LivroDAO();
	
	@Override
	public void inserir(LivroVO entidade) throws SQLException {

		LivroDAO livro = new LivroDAO();
		
		try {
			ResultSet resposta = livro.buscarPorId(entidade);
			if (resposta.next()) {
				throw new Exception("Já existe livro cadastrado com esse ID.");
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
	public void editar(LivroVO entidade) throws SQLException {
		
		LivroDAO livro = new LivroDAO();
		
		try {
			ResultSet resposta = livro.buscarPorId(entidade);
			if (!resposta.next()) {
				throw new Exception("Não existe esse livro.");
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
	public void remover(LivroVO entidade) throws SQLException {

		LivroDAO livro = new LivroDAO();
		
		try {
			ResultSet resposta = livro.buscarPorId(entidade);
			if (!resposta.next()) {
				throw new Exception("Não existe esse livro.");
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
	public List<LivroVO> buscarTodos() throws SQLException {
		
		try {
			ResultSet resposta = dao.buscarTodos();
			if (!resposta.next()) {
				throw new Exception("Não existe Livros.");
			}
			else {
				
				List<LivroVO> livros = new ArrayList<LivroVO>();
				
				while(resposta.next()) {
					
					LivroVO livroRecebe = new LivroVO();
					
					livroRecebe.setId(resposta.getInt("id"));
					livroRecebe.setTitulo(resposta.getString("titulo"));
					livroRecebe.setAutor(resposta.getString("autor"));
					livroRecebe.setEstilo(resposta.getString("genero"));
					livroRecebe.setAno(resposta.getInt("ano"));
					livroRecebe.setPagina(resposta.getInt("paginas"));
					livroRecebe.setValor(resposta.getDouble("valor"));
					livroRecebe.setQuantidade(resposta.getInt("quantidade"));
					livros.add(livroRecebe);
					
				}
				
				return livros;
			}
		}
		catch (Exception erro) {
		      System.err.println(erro);
		      return null;
		}
	}
	
	
	public List<LivroVO> buscarPorAutor(LivroVO liv) throws SQLException {
		
		LivroDAO livro = new LivroDAO();
		
		try {
			ResultSet resposta = livro.buscarPorAutor(liv);
			if (!resposta.next()) {
				throw new Exception("Não existe esse autor.");
			}
			else {
				
				List<LivroVO> livros = new ArrayList<LivroVO>();
				
				while(resposta.next()) {
					
					LivroVO livroRecebe = new LivroVO();
					
					livroRecebe.setId(resposta.getInt("id"));
					livroRecebe.setTitulo(resposta.getString("titulo"));
					livroRecebe.setAutor(resposta.getString("autor"));
					livroRecebe.setEstilo(resposta.getString("genero"));
					livroRecebe.setAno(resposta.getInt("ano"));
					livroRecebe.setPagina(resposta.getInt("paginas"));
					livroRecebe.setValor(resposta.getDouble("valor"));
					livroRecebe.setQuantidade(resposta.getInt("quantidade"));
					livros.add(livroRecebe);
					
				}
				
				return livros;
			}
		}
		catch (Exception erro) {
		      System.err.println(erro);
		      return null;
		}
	}
		
	public List<LivroVO> buscarPorAno(LivroVO liv) throws SQLException {
		
		LivroDAO livro = new LivroDAO();
		
		try {
			ResultSet resposta = livro.buscarPorAno(liv);
			if (!resposta.next()) {
				throw new Exception("Não existe nenhum livro cadastrado desse ano.");
			}
			else {
				
				List<LivroVO> livros = new ArrayList<LivroVO>();
				
				while(resposta.next()) {
					
					LivroVO livroRecebe = new LivroVO();
					
					livroRecebe.setId(resposta.getInt("id"));
					livroRecebe.setTitulo(resposta.getString("titulo"));
					livroRecebe.setAutor(resposta.getString("autor"));
					livroRecebe.setEstilo(resposta.getString("genero"));
					livroRecebe.setAno(resposta.getInt("ano"));
					livroRecebe.setPagina(resposta.getInt("paginas"));
					livroRecebe.setValor(resposta.getDouble("valor"));
					livroRecebe.setQuantidade(resposta.getInt("quantidade"));
					livros.add(livroRecebe);
					
				}
				
				return livros;
			}
		}
		catch (Exception erro) {
		      System.err.println(erro);
		      return null;
		}
	}
	
	public List<LivroVO> buscarPorGenero(LivroVO liv) throws SQLException {
		
		LivroDAO livro = new LivroDAO();
		
		try {
			ResultSet resposta = livro.buscarPorGenero(liv);
			if (!resposta.next()) {
				throw new Exception("Não existe nenhum livro cadastrado com desse genero.");
			}
			else {
				
				List<LivroVO> livros = new ArrayList<LivroVO>();
				
				while(resposta.next()) {
					
					LivroVO livroRecebe = new LivroVO();
					
					livroRecebe.setId(resposta.getInt("id"));
					livroRecebe.setTitulo(resposta.getString("titulo"));
					livroRecebe.setAutor(resposta.getString("autor"));
					livroRecebe.setEstilo(resposta.getString("genero"));
					livroRecebe.setAno(resposta.getInt("ano"));
					livroRecebe.setPagina(resposta.getInt("paginas"));
					livroRecebe.setValor(resposta.getDouble("valor"));
					livroRecebe.setQuantidade(resposta.getInt("quantidade"));
					livros.add(livroRecebe);
					
				}
				
				return livros;
			}
		}
		catch (Exception erro) {
		      System.err.println(erro);
		      return null;
		}
	}
	
	public List<LivroVO> buscarPorTitulo(LivroVO liv) throws SQLException {
	
	LivroDAO livro = new LivroDAO();
	
	try {
		ResultSet resposta = livro.buscarPorTitulo(liv);
		if (!resposta.next()) {
			throw new Exception("Não existe nenhum livro cadastrado com esse titulo.");
		}
		else {
			
			List<LivroVO> livros = new ArrayList<LivroVO>();
			
			while(resposta.next()) {
				
				LivroVO livroRecebe = new LivroVO();
				
				livroRecebe.setId(resposta.getInt("id"));
				livroRecebe.setTitulo(resposta.getString("titulo"));
				livroRecebe.setAutor(resposta.getString("autor"));
				livroRecebe.setEstilo(resposta.getString("genero"));
				livroRecebe.setAno(resposta.getInt("ano"));
				livroRecebe.setPagina(resposta.getInt("paginas"));
				livroRecebe.setValor(resposta.getDouble("valor"));
				livroRecebe.setQuantidade(resposta.getInt("quantidade"));
				livros.add(livroRecebe);
				
			}
			
			return livros;
		}
	}
	catch (Exception erro) {
	      System.err.println(erro);
	      return null;
	}
  }
}