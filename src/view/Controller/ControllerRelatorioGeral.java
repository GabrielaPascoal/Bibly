package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.BO.AluguelBO;
import view.Modals;

public class ControllerRelatorioGeral implements Initializable {

  @FXML
  public void voltar(ActionEvent event) throws IOException {
    Modals.abrirModal("relatorioModal");
  }

  @FXML
  public void gerarRelatorio(ActionEvent event) throws IOException, DocumentException, SQLException {
    AluguelBO aluguelBO = new AluguelBO();
    aluguelBO.gerarRelatorioGeral();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

  }

  public static String selecionarPath() {
    JFileChooser chooser;

    chooser = new JFileChooser();
    chooser.setDialogTitle("Selecione o local para salvar o PDF");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    chooser.setAcceptAllFileFilterUsed(false);

    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      return chooser.getCurrentDirectory().getPath();
    } else {
      return null;
    }
  }

}
