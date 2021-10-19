package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.LivroBO;
import model.VO.LivroVO;
import view.Telas;

public class ControlerLivros implements Initializable {

	@FXML private TableView<LivroVO> tableLivros;

	@FXML private TableColumn<LivroVO, String> clnAutor;
	@FXML private TableColumn<LivroVO, String> clnTitulo;
	@FXML private TableColumn<LivroVO, String> clnGenero;
	@FXML private TableColumn<LivroVO, Integer> clnAno;
	@FXML private TableColumn<LivroVO, Integer> clnPaginas;
	@FXML private TableColumn<LivroVO, Integer> clnQt;
	@FXML private TableColumn<LivroVO, Double> clnValor;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			initTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void voltarlivro(ActionEvent event) throws IOException {
		Telas.telaInicial();
	}

	@FXML
	public void inserirLivro(ActionEvent event) throws IOException {
		Telas.telaInserirLivro();
	}

	@FXML
	public void initTable() throws SQLException {

		clnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		clnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		clnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		clnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		clnPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		clnQt.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		clnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		
		tableLivros.setItems(atualizarTable());

	}

	@FXML
	public ObservableList<LivroVO> atualizarTable() throws SQLException {

		LivroBO livro = new LivroBO();
		return FXCollections.observableArrayList(livro.buscarTodos());

	}

}
