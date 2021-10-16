package view.Controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.Telas;

public class ControlerInserirLivros {

	@FXML private TextField autorTf;
	@FXML private TextField generoTf; 
	@FXML private TextField obsTf;
	@FXML private TextField paginasTf;
	@FXML private TextField quantidadeTf;
	@FXML private TextField tituloTf;
	@FXML private TextField valorTf;
	
	
	@FXML 
	public void salvar(ActionEvent event) {

	}

	@FXML
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaLivro();
	}
}
