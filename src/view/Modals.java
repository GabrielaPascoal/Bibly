package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Modals {
  private static Stage modal;

  public static Stage getModal() {
    return modal;
  }

  public static void setModal(Stage mod) {
    modal = mod;
  }

  public static void abrirModal(String novoModal) throws IOException {
    if (modal == null) {
      Stage novoStage = new Stage();
      setModal(novoStage);
    }

    Parent root = FXMLLoader.load(Telas.class.getResource(novoModal + ".fxml"));
    Scene cena = new Scene(root);

    modal.setTitle(novoModal);
    modal.setScene(cena);
    modal.show();
  }
}
