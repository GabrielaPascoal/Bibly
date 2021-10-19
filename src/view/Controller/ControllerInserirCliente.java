package view.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.Telas;

import java.io.IOException;

import javafx.event.ActionEvent;

public class ControllerInserirCliente {
	
	
	@FXML private TextField nome; 
	@FXML private TextField cpf;
	@FXML private TextField endereco;
	@FXML private TextField celular;
	
	
	@FXML
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaCliente();
	}
	
	@FXML 
	public void salvarCliente(ActionEvent event) {

	}

}
