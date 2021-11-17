package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import com.itextpdf.text.DocumentException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.BO.AluguelBO;
import view.Modals;

public class ControllerRelatorioGeral implements Initializable {

  @FXML
  private DatePicker dataInicial;
  @FXML
  private DatePicker dataFinal;

  @FXML
  private Label erroDatas;

  @FXML
  public void voltar(ActionEvent event) throws IOException {
    Modals.abrirModal("relatorioModal");
  }

  @FXML
  public void gerarRelatorio(ActionEvent event) throws IOException, DocumentException, SQLException {
    AluguelBO aluguelBO = new AluguelBO();
    boolean erroData = false;
    erroData = dataInicial.getValue() == null && dataFinal.getValue() != null
        || dataFinal.getValue() == null && dataInicial.getValue() != null;

    if (erroData) {
      erroDatas.setText("Preencha as duas datas ou nenhuma");
      return;
    }
    erroDatas.setText("");

    aluguelBO.gerarRelatorioGeral(dataInicial.getValue(), dataFinal.getValue());
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
