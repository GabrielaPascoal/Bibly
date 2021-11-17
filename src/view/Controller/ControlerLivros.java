package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.BO.LivroBO;
import model.VO.LivroVO;
import view.Telas;

public class ControlerLivros implements Initializable {

	@FXML
	private TextField busca;

	@FXML
	private CheckBox tituloCheck;
	@FXML
	private CheckBox autorCheck;
	@FXML
	private CheckBox generoCheck;
	@FXML
	private CheckBox anoCheck;

	@FXML
	private TableView<LivroVO> tableLivros;

	@FXML
	private TableColumn<LivroVO, String> clnAutor;
	@FXML
	private TableColumn<LivroVO, String> clnTitulo;
	@FXML
	private TableColumn<LivroVO, String> clnGenero;
	@FXML
	private TableColumn<LivroVO, Integer> clnAno;
	@FXML
	private TableColumn<LivroVO, Integer> clnPaginas;
	@FXML
	private TableColumn<LivroVO, Integer> clnQt;
	@FXML
	private TableColumn<LivroVO, Double> clnValor;
	@FXML
	private TableColumn<LivroVO, LivroVO> deletar;
	@FXML
	private TableColumn<LivroVO, LivroVO> editar;

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

	public final String LIXEIRA = "M5 0V1H0V3H1V16C1 16.5304 1.21071 17.0391 1.58579 17.4142C1.96086 17.7893 2.46957 18 3 18H13C13.5304 18 14.0391 17.7893 14.4142 17.4142C14.7893 17.0391 15 16.5304 15 16V3H16V1H11V0H5ZM3 3H13V16H3V3ZM5 5V14H7V5H5ZM9 5V14H11V5H9Z";
	public final String LAPIS = "M12.1 7L13 7.9L3.9 17H3V16.1L12.1 7ZM15.7 1C15.5 1 15.2 1.1 15 1.3L13.2 3.1L16.9 6.9L18.7 5C19.1 4.6 19.1 4 18.7 3.6L16.4 1.3C16.2 1.1 15.9 1 15.7 1ZM12.1 4.2L1 15.2V19H4.8L15.8 7.9L12.1 4.2ZM5 0V3H8V5H5V8H3V5H0V3H3V0H5Z";

	public static LivroVO retorno() {
		return null;

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
		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<LivroVO> filteredData = new FilteredList<>(atualizarTable(), b -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		busca.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(livro -> {
				// Se for vazio exibe tudo

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// comparacao
				String lowerCaseFilter = newValue.toLowerCase();

				// Filtros.
				if (tituloCheck.isSelected()) {
					if (livro.getTitulo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches first name.
					} else
						return false;
				}

				if (autorCheck.isSelected()) {
					if (livro.getAutor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					} else
						return false;
				}

				if (generoCheck.isSelected()) {
					if (livro.getGenero().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					} else
						return false;
				}

				if (anoCheck.isSelected()) {
					if (String.valueOf(livro.getAno()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					} else
						return false;
				}

				// fim filtros.

				// Pesquisa geral.
				if (livro.getTitulo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (livro.getAutor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				// fim pesquisa geral.
				return false; // nao teve resultado
			});
		});

		// FilteredList em uma SortedList.
		SortedList<LivroVO> sortedData = new SortedList<>(filteredData);

		// vincular SortedList a tableLivros
		sortedData.comparatorProperty().bind(tableLivros.comparatorProperty());

		// adicionar SorteadData a tabela
		tableLivros.setItems(sortedData);

		Botao.initButtons(deletar, 25, LIXEIRA, "svg-roxo", (LivroVO livro, ActionEvent event) -> {

			Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
			ButtonType btnSim = new ButtonType("Sim");
			ButtonType btnNao = new ButtonType("Não");
			dialogoExe.setTitle("CONFIRMAÇÃO");
			dialogoExe.setHeaderText("Deseja adicionar esse livro?");
			dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

			dialogoExe.showAndWait().ifPresent(resposta -> {
				if (resposta == btnSim) {
					LivroBO bo = new LivroBO();
					try {
						bo.remover(livro);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						tableLivros.setItems(atualizarTable());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (resposta == btnNao) {
					try {
						tableLivros.setItems(atualizarTable());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		});

		Botao.initButtons(editar, 25, LAPIS, "svg-roxo", (LivroVO livro, ActionEvent event) -> {

			try {
				ControlerEditarLivro test = new ControlerEditarLivro();
				test.setTextField(livro);
				Telas.telaEditarLivro();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		});
	}

	@FXML
	public ObservableList<LivroVO> atualizarTable() throws SQLException {

		LivroBO livro = new LivroBO();
		return FXCollections.observableArrayList(livro.buscarTodos());

	}

}
