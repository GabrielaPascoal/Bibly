package model.BO;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO.AluguelDAO;
import model.DAO.ClienteDAO;
import model.VO.AluguelDiscoVO;
import model.VO.AluguelLivroVO;
import model.VO.AluguelVO;
import model.VO.DiscoVO;
import model.VO.LivroVO;

public class AluguelBO extends BaseBO<AluguelVO> implements BuscarInterBO<AluguelVO> {
  public AluguelVO getAluguel(ResultSet resposta) throws SQLException {
    AluguelVO aluguel = new AluguelVO();
    AluguelLivroBO aluguelLivroBO = new AluguelLivroBO();
    AluguelDiscoBO aluguelDiscoBO = new AluguelDiscoBO();

    AluguelLivroVO livro = new AluguelLivroVO();
    AluguelDiscoVO disco = new AluguelDiscoVO();

    aluguel.setId(resposta.getInt("id"));
    aluguel.setValor(resposta.getDouble("valor"));
    aluguel.setData(resposta.getDate("data").toLocalDate());

    livro.setAluguel(aluguel);

    List<AluguelLivroVO> livros = aluguelLivroBO.buscarPorAluguelId(livro);
    List<AluguelDiscoVO> discos = aluguelDiscoBO.buscarPorAluguelId(disco);

    aluguel.setLivros(livros);
    aluguel.setDiscos(discos);
    // aluguel.setCliente();

    return aluguel;
  }

  public void inserir(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();
      ClienteDAO clienteDAO = new ClienteDAO();

      if (aluguel.getCliente().getId() <= 0) {
        throw new Exception("Defina o id de um cliente valido.");
      }

      if (aluguel.getValor() < 0) {
        throw new Exception("Valor nao pode ser menor que 0.");
      }

      ResultSet respostaCliente = clienteDAO.buscarPorId(aluguel.getCliente());

      if (!respostaCliente.next()) {
        throw new Exception("Cliente nao existe.");
      }

      aluguelDAO.inserir(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public AluguelVO buscarPorId(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getId() <= 0) {
        throw new Exception("Defina um id valido.");
      }

      ResultSet resposta = aluguelDAO.buscarPorId(aluguel);

      if (!resposta.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      return getAluguel(resposta);
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public List<AluguelVO> buscarTodos() throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();
      ResultSet resposta = aluguelDAO.buscarTodos();

      List<AluguelVO> alugueis = new ArrayList<AluguelVO>();

      while (resposta.next()) {
        AluguelVO aluguel = getAluguel(resposta);
        alugueis.add(aluguel);
      }

      return alugueis;
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public List<AluguelVO> buscarPorMes(Integer mes) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (mes < 1 && mes > 12) {
        throw new Exception("Escolha um mes de 1 a 12.");
      }

      return aluguelDAO.buscarPorMes(mes);
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public List<AluguelVO> buscarPorCliente(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getCliente().getId() <= 0) {
        throw new Exception("Defina o id de um cliente valido.");
      }

      return aluguelDAO.buscarPorCliente(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public List<AluguelVO> buscarPorIntervaloDeDias(LocalDate dataMin, LocalDate dataMax) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (dataMin == null && dataMax == null) {
        throw new Exception("Defina uma data valida.");
      }

      return aluguelDAO.buscarPorIntervaloDeDias(dataMin, dataMax);
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public void editar(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();
      ClienteDAO clienteDAO = new ClienteDAO();

      if (aluguel.getId() <= 0) {
        throw new Exception("Defina um id valido.");
      }

      ResultSet resposta = aluguelDAO.buscarPorId(aluguel);

      if (!resposta.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      AluguelVO aluguelAntigo = getAluguel(resposta);

      if (aluguel.getCliente().getId() <= 0) {
        aluguel.setCliente(aluguelAntigo.getCliente());
      }

      if (aluguel.getValor() < 0) {
        aluguel.setValor(aluguelAntigo.getValor());
      }

      ResultSet respostaCliente = clienteDAO.buscarPorId(aluguel.getCliente());

      if (!respostaCliente.next()) {
        throw new Exception("Cliente nao existe.");
      }

      aluguelDAO.editar(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
  }

  public void remover(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getId() <= 0) {
        throw new Exception("Defina um id valido.");
      }

      ResultSet resposta = aluguelDAO.buscarPorId(aluguel);

      if (!resposta.next()) {
        throw new Exception("Aluguel nao existe.");
      }

      aluguelDAO.remover(aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
    }
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

  public Document gerarPDF(String nomePdf) throws FileNotFoundException, DocumentException, IOException {
    String path = selecionarPath();
    Document documento = new Document();

    PdfWriter.getInstance(documento, new FileOutputStream(path + "/" + nomePdf + ".pdf"));

    documento.open();

    Paragraph titulo = new Paragraph("Relatório");
    titulo.setAlignment(Element.ALIGN_CENTER);
    documento.add(titulo);
    documento.add(Chunk.NEWLINE);
    documento.add(Chunk.NEWLINE);

    return documento;
  }

  public void gerarRelatorioGeral() throws DocumentException, SQLException, IOException, NullPointerException {
    List<AluguelVO> alugueis = this.buscarTodos();
    Integer totalDeAlugueis = alugueis.size();

    LocalDateTime dataHoraAtual = LocalDateTime.now();
    DateTimeFormatter formatadorNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    DateTimeFormatter formatadorDataAluguel = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Document relatorio = gerarPDF("RelatorioGeral-Bibly" + dataHoraAtual.format(formatadorNomeArquivo));

    if (totalDeAlugueis <= 0) {
      relatorio.close();
      return;
    }

    for (int i = 0; i < totalDeAlugueis; i++) {
      AluguelVO aluguel = alugueis.get(i);
      relatorio.add(new Paragraph("ID do aluguel-" + aluguel.getId()));
      relatorio.add(new Paragraph("  -Valor total: R$" + aluguel.getValor()));
      relatorio.add(new Paragraph("  -Data: " + aluguel.getData().format(formatadorDataAluguel)));
      relatorio.add(Chunk.NEWLINE);

      // relatorio.add(new Paragraph("ID do aluguel-" + aluguel.getId()));
      // relatorio.add(new Paragraph(" Livros:"));

      // List<AluguelLivroVO> aluguelLivros = aluguel.getLivros();
      // Integer qtdLivrosAlugados = aluguelLivros.size();

      // if (qtdLivrosAlugados >= 0) {
      // for (int n = 0; n < qtdLivrosAlugados; n++) {
      // AluguelLivroVO aluguelLivro = aluguelLivros.get(n);
      // LivroVO livro = new LivroBO().buscarPorId(aluguelLivro.getProduto()).get(0);

      // relatorio.add(new Paragraph(" Título: "));
      // relatorio.add(new Paragraph(" Quantidade: "));
      // relatorio.add(new Paragraph(" Valor unitário: "));
      // relatorio.add(new Paragraph(" Valor Total: "));
      // relatorio.add(Chunk.NEWLINE);
      // }
      // }

      // List<AluguelDiscoVO> aluguelDiscos = aluguel.getDiscos();
      // Integer qtdDiscosAlugados = aluguelDiscos.size();

      // if (qtdDiscosAlugados >= 0) {
      // for (int n = 0; n < qtdDiscosAlugados; n++) {
      // AluguelDiscoVO aluguelDisco = aluguelDiscos.get(n);
      // DiscoVO disco = new DiscoBO().buscarPorId(aluguelDisco.getProduto()).get(0);

      // relatorio.add(new Paragraph(" Título: "));
      // relatorio.add(new Paragraph(" Quantidade: "));
      // relatorio.add(new Paragraph(" Valor unitário: "));
      // relatorio.add(new Paragraph(" Valor Total: "));
      // relatorio.add(Chunk.NEWLINE);
      // }
      // }
    }

    relatorio.close();
  }
}
