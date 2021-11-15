package view.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.BO.ClienteBO;
import model.VO.ClienteVO;
import view.Telas;

import java.io.IOException;
import java.sql.SQLException;

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
		ClienteVO cliente = new ClienteVO();
		ClienteBO bo = new ClienteBO();
		
		cliente.setNome(nome.getText());
		cliente.setCpf(cpf.getText());
		cliente.setEndereco(endereco.getText());
		cliente.setCelular(celular.getText());
		
		
		try {
			bo.inserir(cliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

}
