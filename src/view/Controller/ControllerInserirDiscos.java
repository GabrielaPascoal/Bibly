package view.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.BO.DiscoBO;
import model.VO.DiscoVO;
import view.Telas;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;

public class ControllerInserirDiscos {
	
	@FXML private TextField titulo;
	@FXML private TextField nome; 
	@FXML private TextField estilo;
	@FXML private TextField quantidade;
	@FXML private TextField valor;
	@FXML private TextField obs;
	
	@FXML 
	public void salvar(ActionEvent event) {
		
		DiscoVO disco = new DiscoVO();
		DiscoBO bo = new DiscoBO();
		
		disco.setTitulo(titulo.getText());
		disco.setArtista(nome.getText());
		disco.setEstilo(estilo.getText());
		disco.setQuantidade(Integer.valueOf(quantidade.getText()));
		disco.setValor(Double.valueOf(valor.getText().replaceAll("," ,".")));
		
		try {
			bo.inserir(disco);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	@FXML
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaDisco();
	}
	
}
	