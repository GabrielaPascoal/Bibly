package view.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.BO.DiscoBO;
import model.BO.LivroBO;
import model.VO.ProdutoInterVO;
import view.Telas;

public class ControllerAdicionarProduto implements Initializable {

  private static List<ProdutoInterVO> listaProduto = new ArrayList<ProdutoInterVO>();

  @FXML
  private TableView<ProdutoInterVO> tabelaProdutos;
  @FXML
  private TableColumn<ProdutoInterVO, Integer> id;
  @FXML
  private TableColumn<ProdutoInterVO, String> produto;
  @FXML
  private TableColumn<ProdutoInterVO, Integer> quantidade;
  @FXML
  private TableColumn<ProdutoInterVO, Double> valor;
  @FXML
  private TableColumn<ProdutoInterVO, ProdutoInterVO> adicionar;
  @FXML
  private ToggleButton tbTipo;
  @FXML
  private AnchorPane tipoLivro;
  @FXML
  private AnchorPane tipoDisco;
  @FXML
  private Label adicionado;

  @FXML
  public void confirmar(ActionEvent event) throws IOException {
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

  public List<ProdutoInterVO> getListaProduto() {
    return listaProduto;
  }

  public void renderizarTabela() throws SQLException {
    initTable();
  }

  public void adicionarCarrinho() throws SQLException {
    ProdutoInterVO produto = tabelaProdutos.getSelectionModel().getSelectedItem();
    boolean podeAdicionar = true;
    if (produto.getQuantidade() <= 0) {
      return;
    }

    for (ProdutoInterVO p : listaProduto) {
      if (p.getClass().equals(produto.getClass())) {
        podeAdicionar = p.getId() == produto.getId() ? false : true;
      }
    }

    if (podeAdicionar) {
      produto.setQuantidade(1);
      listaProduto.add(produto);

      adicionado.setOpacity(1);
      adicionado.setText(produto.getTitulo() + " foi adicionado!");
    }
  }

  private void initTable() throws SQLException {
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    produto.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    tabelaProdutos.setItems(atualizar());
  }

  @FXML
  public ObservableList<ProdutoInterVO> atualizar() throws SQLException {

    if (tbTipo.isSelected()) {
      tipoLivro.setStyle("-fx-background-color: transparent");
      tipoDisco.setStyle("-fx-background-color: #7259c1; -fx-background-radius: 15;");

      DiscoBO disco = new DiscoBO();

      return FXCollections.observableArrayList(disco.buscarTodos());
    }

    tipoLivro.setStyle("-fx-background-color: #7259c1; -fx-background-radius: 15;");
    tipoDisco.setStyle("-fx-background-color: transparent");

    LivroBO livro = new LivroBO();

    return FXCollections.observableArrayList(livro.buscarTodos());
  }
}
