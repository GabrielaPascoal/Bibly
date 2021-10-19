package view.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.Telas;

import java.io.IOException;

import javafx.event.ActionEvent;

public class ControllerInserirDiscos {
	
	@FXML private TextField titulo;
	@FXML private TextField nome; 
	@FXML private TextField estilo;
	@FXML private TextField quantidade;
	@FXML private TextField valor;
	@FXML private TextField obs;
	
	
	@FXML
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaDisco();
	}
	
	@FXML 
	public void salvar(ActionEvent event) {

	}
	
	
}