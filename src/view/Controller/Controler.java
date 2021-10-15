package view.Controller;


import java.io.IOException;

import view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.BO.UsuarioBO;
import model.VO.UsuarioVO;

public class Controler {

	@FXML private ImageView errologin;
	@FXML private Button logout;
	@FXML private TextField cpf;
	@FXML private PasswordField senha;
	
	UsuarioBO test = new UsuarioBO();
	
	
	public void autenticar(ActionEvent event) throws Exception  {
		
		UsuarioVO user = new UsuarioVO();
		user.setCpf(cpf.getText());
		user.setSenha(senha.getText());
		
		
		boolean autenticado = test.autenticar(user);
		
		if(autenticado==false){
			
		}
		else{
			
			Telas.telaLoading();
		}
	}
	
	public void logout(ActionEvent event) throws IOException {
		Telas.telaLogin();
	}
	
	public void livro(ActionEvent event) throws IOException {
		Telas.telaLivro();
	}
	
	public void voltarlivro(ActionEvent event) throws IOException {
		Telas.telaInicial();
	}
	
	public void inserirLivro(ActionEvent event) throws IOException {
		Telas.telaInserirLivro();
	}
	
	
	
}
