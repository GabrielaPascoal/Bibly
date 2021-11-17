package view.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.BO.UsuarioBO;
import model.VO.UsuarioVO;
import view.Telas;

public class ControllerLogin {

	@FXML
	private Label erro;
	@FXML
	private TextField cpf;
	@FXML
	private PasswordField senha;
	@FXML
	private TextField mostrar;
	@FXML
    private Button codificar;
	@FXML
    private Button decodificar;
	UsuarioBO test = new UsuarioBO();

	private int testEntrada;
	
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
			mostrar.clear();
			cpf.requestFocus();
			testEntrada++;
			
			if(testEntrada>2) {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Erro");
				alert.setHeaderText("Erros consecutivos");
				alert.setContentText("Se for novo usuario, a senha e user padrão sao respectivamente 00000000000, 123456");
				alert.show();
			}

		} else {

			Telas.telaLoading();

		}
	}

	@FXML
	void codificar(ActionEvent event) {
		senha.setText(mostrar.getText());
		mostrar.setVisible(false);
		senha.setVisible(true);
		codificar.setVisible(false);
		decodificar.setVisible(true);
	}

	@FXML
	void decodificar(ActionEvent event) {
		mostrar.setText(senha.getText());
		senha.setVisible(false);
		mostrar.setVisible(true);
		decodificar.setVisible(false);
		codificar.setVisible(true);

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
}
