package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.AluguelBO;
import model.BO.ClienteBO;
import model.VO.AluguelVO;
import model.VO.ClienteVO;
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

    // nome.setCellValueFactory(cellData -> new
    // SimpleStringProperty(cellData.getValue().getCliente().getNome()));
    // celular.setCellValueFactory(cellData -> new
    // SimpleStringProperty(cellData.getValue().getCliente().getCelular()));
    dataRetirada.setCellValueFactory(new PropertyValueFactory<>("data"));
    valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    tabelaDevolucao.setItems(atualizar());
  }

  @FXML
  public ObservableList<AluguelVO> atualizar() throws SQLException {
    AluguelBO alugueis = new AluguelBO();

    return FXCollections.observableArrayList(alugueis.buscarTodos());

  }
}
