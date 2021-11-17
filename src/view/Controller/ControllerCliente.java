package view.Controller;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.ClienteBO;
import model.VO.ClienteVO;
import view.Telas;


public class ControllerCliente implements Initializable {
	
	@FXML
	private TextField busca;
	@FXML
	private CheckBox NomeCheck;
	@FXML
	private CheckBox EnderecoCheck;
	@FXML
	private CheckBox CpfCheck;
	
	@FXML private TableView<ClienteVO> tabelaClientes;
	
	@FXML private TableColumn<ClienteVO, String> nome;
	@FXML private TableColumn<ClienteVO, String> cpf;
	@FXML private TableColumn<ClienteVO, String> endereco;
	@FXML private TableColumn<ClienteVO, Integer> celular;
	@FXML private TableColumn<ClienteVO, ClienteVO> deletar;
    @FXML private TableColumn<ClienteVO, ClienteVO> editar;

	
	@FXML
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaInicial();
	}
	
	@FXML
	public void inserirCliente(ActionEvent event) throws IOException {
		Telas.telaInserirCliente();
	}
	
	public final String LIXEIRA = "M5 0V1H0V3H1V16C1 16.5304 1.21071 17.0391 1.58579 17.4142C1.96086 17.7893 2.46957 18 3 18H13C13.5304 18 14.0391 17.7893 14.4142 17.4142C14.7893 17.0391 15 16.5304 15 16V3H16V1H11V0H5ZM3 3H13V16H3V3ZM5 5V14H7V5H5ZM9 5V14H11V5H9Z";
	public final String LAPIS = "M12.1 7L13 7.9L3.9 17H3V16.1L12.1 7ZM15.7 1C15.5 1 15.2 1.1 15 1.3L13.2 3.1L16.9 6.9L18.7 5C19.1 4.6 19.1 4 18.7 3.6L16.4 1.3C16.2 1.1 15.9 1 15.7 1ZM12.1 4.2L1 15.2V19H4.8L15.8 7.9L12.1 4.2ZM5 0V3H8V5H5V8H3V5H0V3H3V0H5Z";
	
	public static ClienteVO retorno() {
		return null;

	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			initTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initTable() throws SQLException {
		// TODO Auto-generated method stub
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		celular.setCellValueFactory(new PropertyValueFactory<>("celular"));
		
	    tabelaClientes.setItems(atualizar());
	    
	 // BUSCA
	    
	 // Wrap the ObservableList in a FilteredList (initially display all data).
	 		FilteredList<ClienteVO> filteredData = new FilteredList<>(atualizar(), b -> true);

	 		// 2. Set the filter Predicate whenever the filter changes.
	 		busca.textProperty().addListener((observable, oldValue, newValue) -> {
	 			filteredData.setPredicate(cliente -> {
	 				// If filter text is empty, display all persons.

	 				if (newValue == null || newValue.isEmpty()) {
	 					return true;
	 				}

	 				// Compare first name and last name of every person with filter text.
	 				String lowerCaseFilter = newValue.toLowerCase();

	 				// Filtros.
	 				if (NomeCheck.isSelected()) {
	 					if (cliente.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	 						return true; // Filter matches first name.
	 					} else
	 						return false;
	 				}

	 				if (EnderecoCheck.isSelected()) {
	 					if (cliente.getEndereco().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	 						return true; // Filter matches last name.
	 					} else
	 						return false;
	 				}


	 				if (CpfCheck.isSelected()) {
	 					if (String.valueOf(cliente.getCpf()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
	 						return true; // Filter matches last name.
	 					} else
	 						return false;
	 				}

	 				// fim filtros.

	 				// Pesquisa geral.
	 				if (cliente.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	 					return true; // Filter matches first name.
	 				} else if (cliente.getEndereco().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	 					return true; // Filter matches last name.
	 				}
	 				// fim pesquisa geral.
	 				return false; // Does not match.
	 			});
	 		});
	 		
	 	// 3. Wrap the FilteredList in a SortedList.
			SortedList<ClienteVO> sortedData = new SortedList<>(filteredData);

			// 4. Bind the SortedList comparator to the TableView comparator.
			// Otherwise, sorting the TableView would have no effect.
			sortedData.comparatorProperty().bind(tabelaClientes.comparatorProperty());

			// 5. Add sorted (and filtered) data to the table.
			tabelaClientes.setItems(sortedData);

	   // BOTÕES

			Botao.initButtons(deletar, 25, LIXEIRA, "svg-roxo", (ClienteVO cliente, ActionEvent event) -> {

				Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
				ButtonType btnSim = new ButtonType("Sim");
				ButtonType btnNao = new ButtonType("Não");
				dialogoExe.setTitle("CONFIRMAÇÃO");
				dialogoExe.setHeaderText("Deseja adicionar esse cliente?");
				dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

				dialogoExe.showAndWait().ifPresent(resposta -> {
					if (resposta == btnSim) {
						ClienteBO bo = new ClienteBO();
						try {
							bo.remover(cliente);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						try {
							tabelaClientes.setItems(atualizar());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					

					} else if (resposta == btnNao) {
						try {
							tabelaClientes.setItems(atualizar());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
			});

			Botao.initButtons(editar, 25, LAPIS, "svg-roxo", (ClienteVO cliente, ActionEvent event) -> {
				
				try {
					ControllerEditarCliente test = new ControllerEditarCliente();
					test.setTextField(cliente);
					Telas.telaEditarCliente();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			});
		}

	public ObservableList<ClienteVO> atualizar() throws SQLException {

		ClienteBO cliente = new ClienteBO();
		return FXCollections.observableArrayList(cliente.buscarTodos());
	}
	
}
