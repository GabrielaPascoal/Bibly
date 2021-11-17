package view.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.BO.LivroBO;
import model.VO.LivroVO;
import view.Telas;

public class ControlerInserirLivros {

	@FXML
	private TextField tituloTf;
	@FXML
	private TextField generoTf;
	@FXML
	private TextField anoTf;
	@FXML
	private TextField paginasTf;
	@FXML
	private TextField autorTf;
	@FXML
	private TextField quantidadeTf;
	@FXML
	private TextField valorTf;
	@FXML
	private TextField obsTf;

	@FXML
	public void salvar(ActionEvent event) {

		LivroVO livro = new LivroVO();
		LivroBO bo = new LivroBO();

		if ((tituloTf.getText() == "") || (autorTf.getText() == "") || (generoTf.getText() == "")
				|| (anoTf.getText() == "") || (paginasTf.getText() == "") || (quantidadeTf.getText() == "")
				|| (valorTf.getText() == "")) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Erro");
			alert.setHeaderText("Espaços vazios.");
			alert.show();
		} else {

			Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
			ButtonType btnSim = new ButtonType("Sim");
			ButtonType btnNao = new ButtonType("Não");
			dialogoExe.setTitle("CONFIRMAÇÃO");
			dialogoExe.setHeaderText("Deseja adicionar esse livro?");
			dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

			dialogoExe.showAndWait().ifPresent(resposta -> {
				if (resposta == btnSim) {

					livro.setTitulo(tituloTf.getText());
					livro.setGenero(generoTf.getText());
					livro.setAno(Integer.valueOf(anoTf.getText()));
					livro.setPagina(Integer.valueOf(paginasTf.getText()));
					livro.setAutor(autorTf.getText());
					livro.setQuantidade(Integer.valueOf(quantidadeTf.getText()));
					livro.setValor(Double.valueOf(valorTf.getText().replaceAll(",", ".")));
					
					
					try {
						bo.inserir(livro);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						Telas.telaLivro();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (resposta == btnNao) {
					try {
						Telas.telaInserirLivro();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});

		}
	}

	@FXML
	public void voltar(ActionEvent event) throws IOException {
		Telas.telaLivro();
	}

}
