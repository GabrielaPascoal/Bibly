package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.AluguelBO;
import model.VO.AluguelVO;
import view.Telas;

public class ControllerDevolucao implements Initializable {

  @FXML
  private TableView<AluguelVO> tabelaDevolucao;
  @FXML
  private TableColumn<AluguelVO, String> nome;
  @FXML
  private TableColumn<AluguelVO, String> celular;
  @FXML
  private TableColumn<AluguelVO, Integer> dataRetirada;
  @FXML
  private TableColumn<AluguelVO, Double> valor;

  @FXML
  public void voltar(ActionEvent event) throws IOException {
    Telas.telaAlugel();
  }

  @FXML
  public void devolver(ActionEvent event) throws IOException, SQLException {
    Alert devolverExe = new Alert(Alert.AlertType.CONFIRMATION);
    ButtonType btnSim = new ButtonType("Sim");
    ButtonType btnNao = new ButtonType("Não");
    devolverExe.setTitle("Devolvendo...");
    devolverExe.setHeaderText("Tem certeza que deseja devolver a compra?");
    devolverExe.getButtonTypes().setAll(btnNao, btnSim);
    String botaoPressionado = devolverExe.showAndWait().get().getText();

    Boolean prosseguir = botaoPressionado == "Sim";

    if (!prosseguir) {
      return;
    }

    AluguelBO aluguelBO = new AluguelBO();
    AluguelVO aluguel = tabelaDevolucao.getSelectionModel().getSelectedItem();
    AluguelVO novoAluguel = new AluguelVO();
    novoAluguel.setId(aluguel.getId());
    novoAluguel.setCliente(aluguel.getCliente());
    novoAluguel.setValor(aluguel.getValor());
    aluguel.setDevolucao(LocalDate.now());

    aluguelBO.editar(aluguel);
    initTable();
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    try {
      initTable();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void initTable() throws SQLException {

    nome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNome()));
    celular.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getCelular()));
    dataRetirada.setCellValueFactory(new PropertyValueFactory<>("data"));
    valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    tabelaDevolucao.setItems(atualizar());
  }

  @FXML
  public ObservableList<AluguelVO> atualizar() throws SQLException {
    AluguelBO aluguelBO = new AluguelBO();
    List<AluguelVO> alugueis = aluguelBO.buscarPorDevolucao();

    return FXCollections.observableArrayList(alugueis);

  }
}
