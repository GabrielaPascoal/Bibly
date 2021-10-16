package view.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.BO.UsuarioBO;
import model.VO.UsuarioVO;
import view.Telas;

public class Controler {

	@FXML private Label erro;
	@FXML private TextField cpf;
	@FXML private PasswordField senha;

	UsuarioBO test = new UsuarioBO();

	public void autenticar(ActionEvent event) throws Exception {

		UsuarioVO user = new UsuarioVO();
		user.setCpf(cpf.getText());
		user.setSenha(senha.getText());

		boolean autenticado = test.autenticar(user);

		if (autenticado == false) {

			erro.setText("CPF ou senha incorretos.");
			erro.setVisible(true);
			cpf.clear();
			senha.clear();
			cpf.requestFocus();

		} else {

			Telas.telaLoading();
			
		}
	}

	@FXML
	public void cpfKeyPressed(KeyEvent event) {

		cpf.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					senha.requestFocus();
				}
			}
		});
	}

	public void logout(ActionEvent event) throws IOException {
		Telas.telaLogin();
	}

	public void livro(ActionEvent event) throws IOException {
		Telas.telaLivro();
	}

}
