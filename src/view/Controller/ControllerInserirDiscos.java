package view.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.BO.DiscoBO;
import model.VO.DiscoVO;
import view.Telas;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;

public class ControllerInserirDiscos {

	@FXML
	private TextField titulo;
	@FXML
	private TextField estilo;
	@FXML
	private TextField artista;
	@FXML
	private TextField quantidade;
	@FXML
	private TextField valor;

	@FXML
	public void salvar(ActionEvent event) {

		DiscoVO disco = new DiscoVO();
		DiscoBO bo = new DiscoBO();

		if ((titulo.getText() == "") || (artista.getText() == "") || (estilo.getText() == "")
				|| (quantidade.getText() == "") || (valor.getText() == "")) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Erro");
			alert.setHeaderText("Espa�os vazios.");
			alert.show();
		} else {

			Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
			ButtonType btnSim = new ButtonType("Sim");
			ButtonType btnNao = new ButtonType("Não");
			dialogoExe.setTitle("CONFIRMA��O");
			dialogoExe.setHeaderText("Deseja adicionar esse disco?");
			dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

			dialogoExe.showAndWait().ifPresent(resposta -> {
				if (resposta == btnSim) {

					disco.setTitulo(titulo.getText());
					disco.setEstilo(estilo.getText());
					disco.setArtista(artista.getText());
					disco.setQuantidade(Integer.valueOf(quantidade.getText()));
					disco.setValor(Double.valueOf(valor.getText().replaceAll(",", ".")));

					try {
						bo.inserir(disco);
						Telas.telaDisco();
					} catch (SQLException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (resposta == btnNao) {
					try {
						Telas.telaInserirDisco();
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
		Telas.telaDisco();
	}

}