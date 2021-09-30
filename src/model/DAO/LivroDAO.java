package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.VO.LivroVO;

public class LivroDAO extends BaseDAO<LivroVO> implements BuscarInterDAO<LivroVO> {

	// Inserir livros.
	public void inserir(LivroVO livro) {

		String sql = "INSERT INTO livros (titulo, autor, genero, ano, paginas, valor, quantidade) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ptst;

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, livro.getTitulo());
			ptst.setString(2, livro.getAutor());
			ptst.setString(3, livro.getEstilo());
			ptst.setInt(4, livro.getAno());
			ptst.setInt(5, livro.getPagina());
			ptst.setDouble(6, livro.getValor());
			ptst.setInt(7, livro.getQuantidade());
			ptst.execute();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// Remover livros cadastrados.
	public void remover(LivroVO livro) {

		String sql = "DELETE FROM livros WHERE id = ?";
		PreparedStatement ptst;

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setInt(1, livro.getId());
			ptst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Editar livros cadastrados.
	public void editar(LivroVO livro) {

		String sql = "UPDATE livros SET titulo = ?, autor = ?, genero = ?, ano = ?, paginas = ?, valor = ?, quantidade = ? WHERE id = ? ";
		PreparedStatement ptst;

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, livro.getTitulo());
			ptst.setString(2, livro.getAutor());
			ptst.setString(3, livro.getEstilo());
			ptst.setInt(4, livro.getAno());
			ptst.setInt(5, livro.getPagina());
			ptst.setDouble(6, livro.getValor());
			ptst.setInt(7, livro.getQuantidade());
			ptst.setInt(8, livro.getId());
			ptst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Buscar todos os livros cadastrados.
	public ResultSet buscarTodos() {

		String sql = "SELECT * FROM livros";
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

	// Buscar livros cadastrados por id.
	public ResultSet buscarPorId(LivroVO livro) {

		String sql = "SELECT * FROM livros WHERE id=?";
		PreparedStatement ptst;
		ResultSet resposta = null;
		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setInt(1, livro.getId());
			resposta = ptst.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resposta;
	}

	// Buscar livros cadastrados por autor.
	public List<LivroVO> buscarPorAutor(LivroVO livro) {

		String sql = "SELECT * FROM livros WHERE autor = ?";
		PreparedStatement ptst;

		List<LivroVO> livrosEncontrados = new ArrayList<LivroVO>();

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, livro.getAutor());
			ResultSet resposta = ptst.executeQuery();

			while (resposta.next()) {
				LivroVO buscarLivro = new LivroVO();

				buscarLivro.setId(resposta.getInt("id"));
				buscarLivro.setTitulo(resposta.getString("titulo"));
				buscarLivro.setAutor(resposta.getString("autor"));
				buscarLivro.setEstilo(resposta.getString("genero"));
				buscarLivro.setAno(resposta.getInt("ano"));
				buscarLivro.setPagina(resposta.getInt("paginas"));
				buscarLivro.setValor(resposta.getDouble("valor"));
				buscarLivro.setQuantidade(resposta.getInt("quantidade"));

				livrosEncontrados.add(buscarLivro);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return livrosEncontrados;

	}

	// Buscar livros cadastrados por titulo.
	public List<LivroVO> buscarPorTitulo(LivroVO livro) {

		String sql = "SELECT * FROM livros WHERE titulo = ?";
		PreparedStatement ptst;

		List<LivroVO> livrosEncontrados = new ArrayList<LivroVO>();

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, livro.getTitulo());
			ResultSet resposta = ptst.executeQuery();

			while (resposta.next()) {
				LivroVO buscarLivro = new LivroVO();

				buscarLivro.setId(resposta.getInt("id"));
				buscarLivro.setTitulo(resposta.getString("titulo"));
				buscarLivro.setAutor(resposta.getString("autor"));
				buscarLivro.setEstilo(resposta.getString("genero"));
				buscarLivro.setAno(resposta.getInt("ano"));
				buscarLivro.setPagina(resposta.getInt("paginas"));
				buscarLivro.setValor(resposta.getDouble("valor"));
				buscarLivro.setQuantidade(resposta.getInt("quantidade"));

				livrosEncontrados.add(buscarLivro);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return livrosEncontrados;

	}

	// Buscar livros cadastrados por genero.
	public List<LivroVO> buscarPorGenero(LivroVO livro) {

		String sql = "SELECT * FROM livros WHERE genero = ?";
		PreparedStatement ptst;

		List<LivroVO> livrosEncontrados = new ArrayList<LivroVO>();

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, livro.getEstilo());
			ResultSet resposta = ptst.executeQuery();

			while (resposta.next()) {
				LivroVO buscarLivro = new LivroVO();

				buscarLivro.setId(resposta.getInt("id"));
				buscarLivro.setTitulo(resposta.getString("titulo"));
				buscarLivro.setAutor(resposta.getString("autor"));
				buscarLivro.setEstilo(resposta.getString("genero"));
				buscarLivro.setAno(resposta.getInt("ano"));
				buscarLivro.setPagina(resposta.getInt("paginas"));
				buscarLivro.setValor(resposta.getDouble("valor"));
				buscarLivro.setQuantidade(resposta.getInt("quantidade"));

				livrosEncontrados.add(buscarLivro);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return livrosEncontrados;

	}

	// Buscar livros cadastrados por ano.
	public List<LivroVO> buscarPorAno(LivroVO livro) {

		String sql = "SELECT * FROM livros WHERE ano = ?";
		PreparedStatement ptst;

		List<LivroVO> livrosEncontrados = new ArrayList<LivroVO>();

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setInt(1, livro.getAno());
			ResultSet resposta = ptst.executeQuery();

			while (resposta.next()) {
				LivroVO buscarLivro = new LivroVO();

				buscarLivro.setId(resposta.getInt("id"));
				buscarLivro.setTitulo(resposta.getString("titulo"));
				buscarLivro.setAutor(resposta.getString("autor"));
				buscarLivro.setEstilo(resposta.getString("genero"));
				buscarLivro.setAno(resposta.getInt("ano"));
				buscarLivro.setPagina(resposta.getInt("paginas"));
				buscarLivro.setValor(resposta.getDouble("valor"));
				buscarLivro.setQuantidade(resposta.getInt("quantidade"));

				livrosEncontrados.add(buscarLivro);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return livrosEncontrados;

	}
}
