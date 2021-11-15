package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.VO.ProdutoInterVO;
import view.Modals;
import view.Telas;

public class ControllerAluguel implements Initializable {

  private List<ProdutoInterVO> listaProduto;

  @FXML
  private TableView<ProdutoInterVO> tabelaAlugueis;
  @FXML
  private TableColumn<ProdutoInterVO, String> produto;
  @FXML
  private TableColumn<ProdutoInterVO, Integer> quantidade;
  @FXML
  private TableColumn<ProdutoInterVO, Double> valor;

  @FXML
  public void voltar(ActionEvent event) throws IOException {
    Telas.telaInicial();
  }

  @FXML
  public void adicionar(ActionEvent event) throws IOException {
    Modals.abrirModal("adicionarProdutoModal");
  }

  @FXML
  public void clientes(ActionEvent event) throws IOException {
    Telas.telaCliente();
  }

  @FXML
  public void devolver(ActionEvent event) throws IOException {
    Telas.telaDevolucao();
  }

  @FXML
  public void relatorios(ActionEvent event) throws IOException {
    Modals.abrirModal("relatorioModal");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
  }

  public void renderizarTabela() throws SQLException {
    ControllerAdicionarProduto controllerAdicionarProduto = new ControllerAdicionarProduto();
    listaProduto = controllerAdicionarProduto.getListaProduto();
    System.out.println(listaProduto.get(0).toString());
    initTable();
  }

  private void initTable() throws SQLException {
    produto.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    tabelaAlugueis.setItems(atualizar());
  }

  @FXML
  public ObservableList<ProdutoInterVO> atualizar() throws SQLException {
    return FXCollections.observableArrayList(listaProduto);

  }
}
