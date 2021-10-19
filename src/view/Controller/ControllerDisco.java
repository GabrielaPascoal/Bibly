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
import model.BO.DiscoBO;
import model.VO.DiscoVO;
import view.Telas;

public class ControllerDisco implements Initializable {

	@FXML private TableView<DiscoVO> tabelaDiscos;
	
	@FXML private TableColumn<DiscoVO, String> titulo;
	@FXML private TableColumn<DiscoVO, String> nome;
	@FXML private TableColumn<DiscoVO, String> estilo;
	@FXML private TableColumn<DiscoVO, Integer> quantidade;
	@FXML private TableColumn<DiscoVO, Double> valor;
	
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
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaInicial();
	}
	
	@FXML 
	public void inserirDisco(ActionEvent event) throws IOException {
		Telas.telaInserirDisco();
	}
	
	@FXML
	private void initTable() throws SQLException {
		// TODO Auto-generated method stub
		titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		nome.setCellValueFactory(new PropertyValueFactory<>("artista"));
		estilo.setCellValueFactory(new PropertyValueFactory<>("estilo"));
		valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tabelaDiscos.setItems(atualizar());

	}

	@FXML
	public ObservableList<DiscoVO> atualizar() throws SQLException {

		DiscoBO disco = new DiscoBO();
		return FXCollections.observableArrayList(disco.buscarTodos());

	}
}