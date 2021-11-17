package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.BO.ClienteBO;
import model.VO.ClienteVO;
import view.Telas;

public class ControllerEditarCliente implements Initializable {

	@FXML
	private Button editar;

	@FXML
	private Button voltar;

	@FXML
	private TextField Tf;
	@FXML
	private TextField nome;
	@FXML
	private TextField endereco;
	@FXML
	private TextField celular;
	@FXML
	private TextField cpf;
	

	static ClienteVO cliente = new ClienteVO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		popular();

	}

	void setTextField( ClienteVO retorno) {
		cliente = retorno;
	}

	void popular() {
		nome.setText(cliente.getNome());
		endereco.setText(cliente.getEndereco());
		cpf.setText(cliente.getCpf());
		celular.setText(cliente.getCelular());
	
		

	}

	@FXML
	void editarCliente(ActionEvent event) {

		if ((nome.getText() == "") || (endereco.getText() == "") || (cpf.getText() == "") || (celular.getText() == "")) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Erro");
			alert.setHeaderText("Espaços vazios.");
			alert.show();

		} else {

			Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
			ButtonType btnSim = new ButtonType("Sim");
			ButtonType btnNao = new ButtonType("Não");
			dialogoExe.setTitle("CONFIRMAÇÃO");
			dialogoExe.setHeaderText("Deseja editar esse cliente?");
			dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

			dialogoExe.showAndWait().ifPresent(resposta -> {
				if (resposta == btnSim) {
					
					ClienteBO bo = new ClienteBO();
					
					cliente.setNome(nome.getText());
					cliente.setEndereco(endereco.getText());
					cliente.setCpf(cpf.getText());
					cliente.setCelular(celular.getText());
					
					try {
						bo.editar(cliente);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						Telas.telaCliente();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (resposta == btnNao) {

					try {
						Telas.telaEditarCliente();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}

			});
		}
		
	}

	@FXML
	void voltar(ActionEvent event) {
		try {
			Telas.telaCliente();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}