package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.BO.LivroBO;
import model.VO.LivroVO;
import view.Telas;

public class ControlerEditarLivro implements Initializable {

	@FXML
	private Button editar;

	@FXML
	private Button voltar;

	@FXML
	private TextField anoTf;
	@FXML
	private TextField autorTf;
	@FXML
	private TextField generoTf;
	@FXML
	private TextField paginasTf;
	@FXML
	private TextField quantidadeTf;
	@FXML
	private TextField tituloTf;
	@FXML
	private TextField valorTf;

	static LivroVO livro = new LivroVO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		popular();

	}

	void setTextField(LivroVO retorno) {
		livro = retorno;
	}

	void popular() {
		tituloTf.setText(livro.getTitulo());
		autorTf.setText(livro.getAutor());
		generoTf.setText(livro.getGenero());
		paginasTf.setText(String.valueOf(livro.getPaginas()));
		quantidadeTf.setText(String.valueOf(livro.getQuantidade()));
		valorTf.setText(String.valueOf(livro.getValor()));
		anoTf.setText(String.valueOf(livro.getAno()));

	}

	@FXML
	void editarLivro(ActionEvent event) {

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
			dialogoExe.setHeaderText("Deseja editar esse livro?");
			dialogoExe.getButtonTypes().setAll(btnSim, btnNao);

			dialogoExe.showAndWait().ifPresent(resposta -> {
				if (resposta == btnSim) {

					LivroBO bo = new LivroBO();

					livro.setTitulo(tituloTf.getText());
					livro.setGenero(generoTf.getText());
					livro.setAno(Integer.valueOf(anoTf.getText()));
					livro.setPagina(Integer.valueOf(paginasTf.getText()));
					livro.setAutor(autorTf.getText());
					livro.setQuantidade(Integer.valueOf(quantidadeTf.getText()));
					livro.setValor(Double.valueOf(valorTf.getText().replaceAll(",", ".")));

					try {
						bo.editar(livro);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						Telas.telaLivro();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (resposta == btnNao) {

					try {
						Telas.telaInserirCliente();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}

			});
		}

	}

	@FXML
	void voltarLivro(ActionEvent event) {
		try {
			Telas.telaLivro();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
