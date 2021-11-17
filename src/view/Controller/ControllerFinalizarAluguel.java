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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.AluguelBO;
import model.BO.AluguelDiscoBO;
import model.BO.AluguelLivroBO;
import model.BO.ClienteBO;
import model.VO.AluguelDiscoVO;
import model.VO.AluguelLivroVO;
import model.VO.AluguelVO;
import model.VO.ClienteVO;
import model.VO.DiscoVO;
import model.VO.LivroVO;
import model.VO.ProdutoInterVO;
import view.Telas;

public class ControllerFinalizarAluguel implements Initializable {

  private static List<ProdutoInterVO> produtosNoCarrinho;
  AluguelVO aluguel = new AluguelVO();
  ClienteVO cliente = new ClienteVO();

  @FXML
  private TableView<ProdutoInterVO> tabelaAlugueis;
  @FXML
  private TableColumn<ProdutoInterVO, String> produto;
  @FXML
  private TableColumn<ProdutoInterVO, Integer> quantidade;
  @FXML
  private TableColumn<ProdutoInterVO, Double> valor;

  @FXML
  private Label valorTotal;

  @FXML
  private TextField cpf;
  @FXML
  private Label nomeCliente;
  @FXML
  private Label cpfCliente;
  @FXML
  private Label enderecoCliente;
  @FXML
  private Label celularCliente;
  @FXML
  private Label erroCliente;

  @FXML
  public void voltar(ActionEvent event) throws IOException {
    Telas.telaAlugel();
  }

  @FXML
  public void clientes(ActionEvent event) throws IOException {
    Telas.telaCliente();
  }

  @FXML
  public void buscarCliente(ActionEvent event) throws IOException, SQLException {
    cliente.setCpf(cpf.getText());
    cliente = new ClienteBO().buscarPorCpf(cliente).get(0);

    if (cliente.getId() <= 0) {
      erroCliente.setText("Cliente não encontrado");
      return;
    }

    nomeCliente.setText(cliente.getNome());
    cpfCliente.setText(cliente.getCpf());
    enderecoCliente.setText(cliente.getEndereco());
    celularCliente.setText(cliente.getCelular());

    aluguel.setCliente(cliente);
  }

  @FXML
  public void finalizar(ActionEvent event) throws IOException, SQLException {

    Alert finalizarExe = new Alert(Alert.AlertType.CONFIRMATION);
    ButtonType btnSim = new ButtonType("Sim");
    ButtonType btnNao = new ButtonType("Não");
    finalizarExe.setTitle("Finalizando...");
    finalizarExe.setHeaderText("Tem certeza que deseja finalizar a compra?");
    finalizarExe.getButtonTypes().setAll(btnNao, btnSim);
    String botaoPressionado = finalizarExe.showAndWait().get().getText();

    Boolean prosseguir = botaoPressionado == "Sim";

    if (!prosseguir) {
      return;
    }

    if (aluguel.getCliente().getId() <= 0) {
      erroCliente.setText("Insira o cpf do cliente");
      return;
    }
    erroCliente.setText("");

    AluguelBO aluguelBO = new AluguelBO();
    AluguelLivroBO aluguelLivroBO = new AluguelLivroBO();
    AluguelDiscoBO aluguelDiscoBO = new AluguelDiscoBO();

    AluguelLivroVO aluguelLivro = new AluguelLivroVO();
    AluguelDiscoVO aluguelDisco = new AluguelDiscoVO();

    LivroVO livro = new LivroVO();
    DiscoVO disco = new DiscoVO();

    aluguelBO.inserir(aluguel);
    List<AluguelVO> alugueis = aluguelBO.buscarPorCliente(aluguel);
    AluguelVO newAluguel = alugueis.get(alugueis.size() - 1);

    for (ProdutoInterVO produto : produtosNoCarrinho) {
      if (produto instanceof LivroVO) {
        livro = (LivroVO) produto;
        aluguelLivro.setAluguel(newAluguel);
        aluguelLivro.setProduto(livro);
        aluguelLivro.setQuantidade(produto.getQuantidade());

        aluguelLivroBO.inserir(aluguelLivro);
      }

      if (produto instanceof DiscoVO) {
        disco = (DiscoVO) produto;
        aluguelDisco.setAluguel(newAluguel);
        aluguelDisco.setProduto(disco);
        aluguelDisco.setQuantidade(produto.getQuantidade());

        aluguelDiscoBO.inserir(aluguelDisco);
      }
    }

    produtosNoCarrinho.clear();
    Telas.telaAlugel();
  }

  @FXML
  public void cancelar(ActionEvent event) throws IOException, SQLException {
    Alert cancelarExe = new Alert(Alert.AlertType.CONFIRMATION);
    ButtonType btnSim = new ButtonType("Sim");
    ButtonType btnNao = new ButtonType("Não");
    cancelarExe.setTitle("Cancelando...");
    cancelarExe.setHeaderText("Tem certeza que deseja cancelar a compra?");
    cancelarExe.getButtonTypes().setAll(btnNao, btnSim);
    String botaoPressionado = cancelarExe.showAndWait().get().getText();

    Boolean prosseguir = botaoPressionado == "Sim";

    if (!prosseguir) {
      return;
    }

    produtosNoCarrinho.clear();
    Telas.telaAlugel();
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    ControllerAluguel controllerAluguel = new ControllerAluguel();
    produtosNoCarrinho = controllerAluguel.getlistaProduto();
    if (produtosNoCarrinho.size() > 0) {
      try {
        initTable();
        calcValorTotal();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  private void calcValorTotal() {
    Double vt = 0.0;

    for (ProdutoInterVO produto : produtosNoCarrinho) {
      vt += produto.getValor();
    }

    valorTotal.setText("R$ " + vt);

    aluguel.setValor(vt);
  }

  private void initTable() throws SQLException {
    produto.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    tabelaAlugueis.setItems(atualizar());
  }

  @FXML
  public ObservableList<ProdutoInterVO> atualizar() throws SQLException {
    return FXCollections.observableArrayList(produtosNoCarrinho);
  }
}
