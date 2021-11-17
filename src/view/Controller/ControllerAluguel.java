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
import model.BO.DiscoBO;
import model.BO.LivroBO;
import model.VO.DiscoVO;
import model.VO.LivroVO;
import model.VO.ProdutoInterVO;
import view.Modals;
import view.Telas;

public class ControllerAluguel implements Initializable {

  private static List<ProdutoInterVO> listaProduto;

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
  public void adicionarProduto(ActionEvent event) throws IOException {
    Telas.telaAdicionarProduto();
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

  @FXML
  public void alugar(ActionEvent event) throws IOException {
    Telas.telaFinalizarAluguel();
  }

  public List<ProdutoInterVO> getlistaProduto() {
    return listaProduto;
  }

  @FXML
  public void acrescentar(ActionEvent event) throws IOException, SQLException {
    ProdutoInterVO produto = tabelaAlugueis.getSelectionModel().getSelectedItem();
    Integer quantidadeAcrescentada = produto.getQuantidade() + 1;
    boolean podeAcrescentar = true;

    List<LivroVO> livros = new LivroBO().buscarTodos();
    List<DiscoVO> discos = new DiscoBO().buscarTodos();

    if (produto instanceof LivroVO) {
      for (LivroVO livro : livros) {
        if (livro.getId() == produto.getId()) {
          podeAcrescentar = quantidadeAcrescentada <= livro.getQuantidade() ? true : false;
        }
      }
    }

    if (produto instanceof DiscoVO) {
      for (DiscoVO disco : discos) {
        if (disco.getId() == produto.getId()) {
          podeAcrescentar = quantidadeAcrescentada <= disco.getQuantidade() ? true : false;
        }
      }
    }

    if (podeAcrescentar) {
      for (ProdutoInterVO p : listaProduto) {
        if (p.equals(produto)) {
          Double novoValor = p.getValor() + (p.getValor() / p.getQuantidade());
          p.setQuantidade(quantidadeAcrescentada);
          p.setValor(novoValor);
        }
      }
    }

    initTable();
  }

  @FXML
  public void decrescentar(ActionEvent event) throws IOException, SQLException {
    ProdutoInterVO produto = tabelaAlugueis.getSelectionModel().getSelectedItem();

    if (produto.getQuantidade() > 1) {
      for (ProdutoInterVO p : listaProduto) {
        if (p.equals(produto)) {
          Double newValue = p.getValor() - (p.getValor() / p.getQuantidade());
          p.setQuantidade(p.getQuantidade() - 1);
          p.setValor(newValue);
        }
      }
    }

    initTable();
  }

  @FXML
  public void remover(ActionEvent event) throws IOException, SQLException {
    int index = tabelaAlugueis.getSelectionModel().getSelectedIndex();

    listaProduto.remove(index);
    initTable();
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    ControllerAdicionarProduto controllerAdicionarProduto = new ControllerAdicionarProduto();
    listaProduto = controllerAdicionarProduto.getListaProduto();
    if (listaProduto.size() > 0) {
      try {
        initTable();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  private void initTable() throws SQLException {
    tabelaAlugueis.getColumns().get(0).setVisible(false);
    tabelaAlugueis.getColumns().get(0).setVisible(true);

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
