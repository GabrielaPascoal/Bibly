package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.BO.AluguelBO;
import model.DAO.AluguelDAO;
import view.Modals;

public class ControllerRelatorioCliente implements Initializable {

  @FXML
  public void voltar(ActionEvent event) throws IOException {
    Modals.abrirModal("relatorioModal");
  }

  @FXML
  public void gerarRelatorio(ActionEvent event) throws IOException, DocumentException {
  }

  // @FXML
  // public void clientes(ActionEvent event) throws IOException {
  // Telas.telaCliente();
  // }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

  }
}
