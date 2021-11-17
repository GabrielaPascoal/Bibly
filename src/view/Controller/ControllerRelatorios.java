package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import view.Modals;

public class ControllerRelatorios implements Initializable {

  @FXML
  public void geral(ActionEvent event) throws IOException {
    Modals.abrirModal("relatorioGeralModal");
  }

  @FXML
  public void clientes(ActionEvent event) throws IOException {
    Modals.abrirModal("relatorioClienteModal");
  }

  @FXML
  public void fatura(ActionEvent event) throws IOException {
    Modals.abrirModal("faturaMensalModal");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub

  }

}
