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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.BO.DiscoBO;
import model.BO.UsuarioBO;
import model.VO.DiscoVO;
import model.VO.UsuarioVO;
import view.Telas;

public class ControlerUsuario implements Initializable {

	@FXML
	private TableView<UsuarioVO> tableUsuario;

	@FXML
	private TableColumn<UsuarioVO, String> clnCpf;

	@FXML
	private TableColumn<UsuarioVO, Integer> clnId;

	@FXML
	private TableColumn<UsuarioVO, String> clnSenha;

	@FXML
	private TextField cpf;

	@FXML
	private TextField senha;

	@FXML
	void cadastro(ActionEvent event) {

		Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
		dialogoExe.setTitle("CONFIRMAÇÃO");
		dialogoExe.setHeaderText("Deseja adicionar esse usuario?");
		dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

		dialogoExe.showAndWait().ifPresent(resposta -> {
			if (resposta == btnSim) {

				UsuarioVO uservo = new UsuarioVO();
				uservo.setCpf(cpf.getText());
				uservo.setSenha(senha.getText());

				UsuarioBO userbo = new UsuarioBO();
				try {
					userbo.inserir(uservo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (resposta == btnNao) {

				cpf.clear();
				senha.clear();

			}

		});

	}


	@FXML
	void excluir(ActionEvent event) {

		Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
		dialogoExe.setTitle("CONFIRMAÇÃO");
		dialogoExe.setHeaderText("Deseja excluir esse usuario?");
		dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

		dialogoExe.showAndWait().ifPresent(resposta -> {
			if (resposta == btnSim) {

				UsuarioBO userbo = new UsuarioBO();
				UsuarioVO uservo = new UsuarioVO();
				uservo.setCpf(cpf.getText());

				try {
					uservo = userbo.buscarPorCpf(uservo);
					userbo.remover(uservo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (resposta == btnNao) {

				cpf.clear();
				senha.clear();

			}

		});

	}

	@FXML
	private void initTable() throws SQLException {

		clnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		clnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
		clnId.setCellValueFactory(new PropertyValueFactory<>("id"));

		tableUsuario.setItems(atualizar());

	}

	@FXML
	void select(MouseEvent event) {

		int select = tableUsuario.getSelectionModel().getSelectedIndex();
		UsuarioVO recebe = (UsuarioVO) tableUsuario.getItems().get(select);
		cpf.setText(recebe.getCpf());
		senha.setText(recebe.getSenha());

	}

	@FXML
	public ObservableList<UsuarioVO> atualizar() throws SQLException {

		UsuarioBO usuario = new UsuarioBO();
		return FXCollections.observableArrayList(usuario.buscarTodos());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		try {
			initTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
