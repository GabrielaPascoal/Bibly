package view.Controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.ClienteBO;
import model.VO.ClienteVO;

import view.Telas;


public class ControllerCliente implements Initializable {
	
	@FXML
	private TableView<ClienteVO> tabelaClientes;
	
	@FXML private TableColumn<ClienteVO, String> nome;
	@FXML private TableColumn<ClienteVO, String> cpf;
	@FXML private TableColumn<ClienteVO, String> endereco;
	@FXML private TableColumn<ClienteVO, Integer> celular;

	@FXML
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaInicial();
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
		
	}

	public ObservableList<ClienteVO> atualizar() throws SQLException {

		ClienteBO cliente = new ClienteBO();
		return FXCollections.observableArrayList(cliente.buscarTodos());
	}
	
}
