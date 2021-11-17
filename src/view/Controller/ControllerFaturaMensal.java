package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import com.itextpdf.text.DocumentException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BO.AluguelBO;
import view.Modals;

public class ControllerFaturaMensal implements Initializable {

  @FXML
  private ChoiceBox<String> mes;
  @FXML
  private Label erroMes;

  @FXML
  private TextField ano;
  @FXML
  private Label erroAno;

  @FXML
  public void voltar(ActionEvent event) throws IOException {
    Modals.abrirModal("relatorioModal");
  }

  @FXML
  public void gerarRelatorio(ActionEvent event) throws IOException, DocumentException, SQLException {
    Integer anoValue = Integer.parseInt(ano.getText());

    if (mes.getValue().isEmpty()) {
      erroAno.setText("Informe um mes");
      return;
    }

    if (ano.getText().trim().isEmpty()) {
      erroAno.setText("Informe um ano");
      return;
    }

    if (anoValue <= 0) {
      erroAno.setText("Insira um Ano válido");
      return;
    }

    erroMes.setText("");
    erroAno.setText("");

    LocalDate dataInicial = LocalDate.of(anoValue, mes.getSelectionModel().getSelectedIndex() + 1, 1);
    LocalDate dataFinal = dataInicial.withDayOfMonth(dataInicial.lengthOfMonth());

    System.out.println(dataInicial);
    System.out.println(dataFinal);

    new AluguelBO().gerarFaturaMensal(dataInicial, dataFinal);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<String> meses = FXCollections.observableArrayList();
    meses.addAll("Jan", "Fev", "Mar", "Abr", "Maio", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez");
    mes.setItems(meses);
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
