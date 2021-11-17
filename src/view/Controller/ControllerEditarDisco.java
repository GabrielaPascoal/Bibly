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
import model.BO.DiscoBO;
import model.VO.DiscoVO;
import view.Telas;

public class ControllerEditarDisco implements Initializable {

	@FXML
	private Button editar;

	@FXML
	private Button voltar;

	@FXML
	private TextField Tf;
	@FXML
	private TextField artista;
	@FXML
	private TextField estilo;
	@FXML
	private TextField quantidade;
	@FXML
	private TextField titulo;
	@FXML
	private TextField valor;

	static DiscoVO disco = new DiscoVO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		popular();

	}

	void setTextField( DiscoVO retorno) {
		disco = retorno;
	}

	void popular() {
		titulo.setText(disco.getTitulo());
		artista.setText(disco.getArtista());
		estilo.setText(disco.getEstilo());
		quantidade.setText(String.valueOf(disco.getQuantidade()));
		valor.setText(String.valueOf(disco.getValor()));
		

	}

	@FXML
	void editarDisco(ActionEvent event) {

		if ((titulo.getText() == "") || (artista.getText() == "") || (estilo.getText() == "") || (quantidade.getText() == "")
				|| (valor.getText() == "")) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Erro");
			alert.setHeaderText("Espaços vazios.");
			alert.show();

		} else {

			Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
			ButtonType btnSim = new ButtonType("Sim");
			ButtonType btnNao = new ButtonType("Não");
			dialogoExe.setTitle("CONFIRMAÇÃO");
			dialogoExe.setHeaderText("Deseja editar esse disco?");
			dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

			dialogoExe.showAndWait().ifPresent(resposta -> {
				if (resposta == btnSim) {
					
					DiscoBO bo = new DiscoBO();
					
					disco.setTitulo(titulo.getText());
					disco.setEstilo(estilo.getText());
					disco.setArtista(artista.getText());
					disco.setQuantidade(Integer.valueOf(quantidade.getText()));
					disco.setValor(Double.valueOf(valor.getText().replaceAll(",", ".")));
					
					try {
						bo.editar(disco);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						Telas.telaDisco();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (resposta == btnNao) {

					try {
						Telas.telaEditarDisco();
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
			Telas.telaDisco();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
