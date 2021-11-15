package view.Controller;


import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.BO.LivroBO;
import model.VO.LivroVO;
import view.Telas;

public class ControlerInserirLivros {

	@FXML private TextField tituloTf;
	@FXML private TextField generoTf; 
	@FXML private TextField anoTf;
	@FXML private TextField paginasTf;
	@FXML private TextField autorTf;
	@FXML private TextField quantidadeTf;
	@FXML private TextField valorTf; 
	@FXML private TextField obsTf;
	
	@FXML 
	public void salvar(ActionEvent event) {
		
		LivroVO livro = new LivroVO();
		LivroBO bo = new LivroBO();
		
		livro.setTitulo(tituloTf.getText());
		livro.setGenero(generoTf.getText());
		livro.setAno(Integer.valueOf(anoTf.getText()));
		livro.setPagina(Integer.valueOf(paginasTf.getText()));
		livro.setAutor(autorTf.getText());
		livro.setQuantidade(Integer.valueOf(quantidadeTf.getText()));
		livro.setValor(Double.valueOf(valorTf.getText().replaceAll("," ,".")));
		
		try {
			bo.inserir(livro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@FXML
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaLivro();
	}
	
}
