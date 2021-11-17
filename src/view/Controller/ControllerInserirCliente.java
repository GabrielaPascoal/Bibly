package view.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.BO.ClienteBO;
import model.VO.ClienteVO;
import view.Telas;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;

public class ControllerInserirCliente {

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

	@FXML
	public void salvarCliente(ActionEvent event) {

		ClienteVO cliente = new ClienteVO();
		ClienteBO bo = new ClienteBO();

		if ((nome.getText() == "") || (endereco.getText() == "") || (cpf.getText() == "") || (celular.getText() == "")) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Erro");
			alert.setHeaderText("Espa�os vazios.");
			alert.show();
		} else {

			Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
			ButtonType btnSim = new ButtonType("Sim");
			ButtonType btnNao = new ButtonType("N�o");
			dialogoExe.setTitle("CONFIRMA��O");
			dialogoExe.setHeaderText("Deseja adicionar esse cliente?");
			dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

			dialogoExe.showAndWait().ifPresent(resposta -> {
				if (resposta == btnSim) {

					cliente.setNome(nome.getText());
					cliente.setEndereco(endereco.getText());
					cliente.setCpf(cpf.getText());
					cliente.setCelular(celular.getText());

					try {
						bo.inserir(cliente);
						Telas.telaCliente();
					} catch (SQLException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (resposta == btnNao) {
					try {
						Telas.telaInserirCliente();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		}
	}

	@FXML
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaCliente();
	}

}
