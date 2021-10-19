package view.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.Telas;

public class ControllerInicial {
	
	@FXML
	public void logout(ActionEvent event) throws IOException {
		Telas.telaLogin();
	}

	@FXML
	public void livro(ActionEvent event) throws IOException {
		Telas.telaLivro();
	}
	
	@FXML
    public void disco(ActionEvent event) throws IOException  {
		Telas.telaDisco();
    }
	
	@FXML
    public void cliente(ActionEvent event) throws IOException  {
		Telas.telaCliente();
    }

}
