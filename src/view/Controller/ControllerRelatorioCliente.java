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
import javafx.scene.control.TextField;
import model.BO.AluguelBO;
import model.BO.ClienteBO;
import model.VO.AluguelVO;
import model.VO.ClienteVO;
import view.Modals;

public class ControllerRelatorioCliente implements Initializable {

  @FXML
  TextField cpf;
  @FXML
  Label erroCliente;

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
    AluguelVO aluguel = new AluguelVO();
    ClienteVO cliente = new ClienteVO();
    ClienteBO clienteBO = new ClienteBO();
    boolean erroData = false;

    cliente.setCpf(cpf.getText());
    cliente = clienteBO.buscarPorCpf(cliente).get(0);

    erroData = dataInicial.getValue() == null && dataFinal.getValue() != null
        || dataFinal.getValue() == null && dataInicial.getValue() != null;

    if (cpf.getText().trim().isEmpty()) {
      erroCliente.setText("Informe um cliente");
      return;
    }

    if (cliente.getId() <= 0) {
      erroCliente.setText("Insira o cpf do cliente");
      return;
    }

    if (erroData) {
      erroDatas.setText("Preencha as duas datas ou nenhuma");
      return;
    }
    erroCliente.setText("");
    erroDatas.setText("");

    aluguel.setCliente(cliente);

    aluguelBO.gerarRelatorioPorCliente(dataInicial.getValue(), dataFinal.getValue(), aluguel);
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
