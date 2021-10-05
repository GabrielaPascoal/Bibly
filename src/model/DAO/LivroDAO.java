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
	public ResultSet buscarPorAutor(LivroVO livro) {

		String sql = "SELECT * FROM livros WHERE autor = ?";
		PreparedStatement ptst;
		ResultSet resposta = null;
		

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, livro.getAutor());
			resposta = ptst.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resposta;
	}

	// Buscar livros cadastrados por titulo.
	public ResultSet buscarPorTitulo(LivroVO livro) {

		String sql = "SELECT * FROM livros WHERE titulo = ?";
		PreparedStatement ptst;
		ResultSet resposta = null;

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, livro.getTitulo());
			resposta = ptst.executeQuery();


		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resposta;

	}

	// Buscar livros cadastrados por genero.
	public ResultSet buscarPorGenero(LivroVO livro) {

		String sql = "SELECT * FROM livros WHERE genero = ?";
		PreparedStatement ptst;
		ResultSet resposta = null;

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setString(1, livro.getEstilo());
			resposta = ptst.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resposta;

	}

	// Buscar livros cadastrados por ano.
	public ResultSet buscarPorAno(LivroVO livro) {

		String sql = "SELECT * FROM livros WHERE ano = ?";
		PreparedStatement ptst;
		ResultSet resposta = null;
		

		try {

			Connection connection = getConnection();
			ptst = connection.prepareStatement(sql);
			ptst.setInt(1, livro.getAno());
			resposta = ptst.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resposta;

	}
}
