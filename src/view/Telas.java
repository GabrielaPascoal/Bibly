package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Telas extends Application {

	private static Stage pS;

	public static Stage getpS() {
		return pS;
	}

	public static void setpS(Stage pS) {
		Telas.pS = pS;
	}

	@Override
	public void start(Stage pS) throws Exception {
		setpS(pS);
		pS.setTitle("login");
		pS.show();
		telaLoading();
	}

	public static void telaAlugel() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("aluguelPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);
	}
	
	public static void telaUsuario() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("UsuarioPage.fxml"));
		Scene cena = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(cena);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
		
	}

	public static void telaAdicionarProduto() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("adicionarProduto.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);
	}

	public static void telaFinalizarAluguel() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("finalizarAluguelPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);
	}

	public static void telaDevolucao() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("devolucaoPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);
	}

	public static void telaLogin() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("loginPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);
	}

	public static void telaLoading() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("loadingPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);
		telaInicial();
	}

	public static void telaInicial() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("inicialPage.fxml"));
		Scene cena = new Scene(root);
		pS.setTitle("Bibly");
		pS.setScene(cena);
	}

	public static void telaLivro() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("livroPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);
	}

	public static void telaInserirLivro() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("inserirLivroPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);

	}

	public static void telaEditarLivro() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("editarLivroPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);

	}

	public static void telaDisco() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("DiscoPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);

	}

	public static void telaInserirDisco() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("InserirDiscoPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);

	}

	public static void telaEditarDisco() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("editarDiscoPage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);

	}

	public static void telaCliente() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("ClientePage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);

	}

	public static void telaInserirCliente() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("InserirClientePage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);

	}

	public static void telaEditarCliente() throws IOException {

		Parent root = FXMLLoader.load(Telas.class.getResource("editarClientePage.fxml"));
		Scene cena = new Scene(root);
		pS.setScene(cena);

	}

	public static void main(String[] args) {
		launch();
	}
}
