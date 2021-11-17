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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO.AluguelDAO;
import model.VO.AluguelDiscoVO;
import model.VO.AluguelLivroVO;
import model.VO.AluguelVO;
import model.VO.ClienteVO;
import utils.formater;

public class AluguelBO extends BaseBO<AluguelVO> implements BuscarInterBO<AluguelVO> {
  public AluguelVO getAluguel(ResultSet resposta) throws SQLException {
    AluguelVO aluguel = new AluguelVO();
    AluguelLivroBO aluguelLivroBO = new AluguelLivroBO();
    AluguelDiscoBO aluguelDiscoBO = new AluguelDiscoBO();
    ClienteBO clienteBO = new ClienteBO();

    AluguelLivroVO livro = new AluguelLivroVO();
    AluguelDiscoVO disco = new AluguelDiscoVO();
    ClienteVO cliente = new ClienteVO();

    aluguel.setId(resposta.getInt("id"));
    aluguel.setValor(resposta.getDouble("valor"));
    aluguel.setData(resposta.getDate("data").toLocalDate());
    if (resposta.getDate("devolucao") != null) {
      aluguel.setDevolucao(resposta.getDate("devolucao").toLocalDate());
    }

    livro.setAluguel(aluguel);
    disco.setAluguel(aluguel);
    cliente.setId(resposta.getInt("cliente_id"));

    List<AluguelLivroVO> livros = aluguelLivroBO.buscarPorAluguelId(livro);
    List<AluguelDiscoVO> discos = aluguelDiscoBO.buscarPorAluguelId(disco);
    cliente = clienteBO.buscarPorId(cliente);

    aluguel.setLivros(livros);
    aluguel.setDiscos(discos);

    aluguel.setCliente(cliente);

    return aluguel;
  }

  public void inserir(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getCliente().getId() <= 0) {
        throw new Exception("Defina o id de um cliente valido.");
      }

      if (aluguel.getValor() < 0) {
        throw new Exception("Valor nao pode ser menor que 0.");
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
      List<AluguelVO> alugueis = new ArrayList<AluguelVO>();

      ResultSet resposta = aluguelDAO.buscarPorDevolucao();

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

  public List<AluguelVO> buscarPorClienteEIntervaloDeDias(LocalDate dataMin, LocalDate dataMax, AluguelVO aluguel)
      throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

      if (aluguel.getCliente().getId() <= 0) {
        throw new Exception("Defina o id de um cliente valido.");
      }

      if (dataMin == null && dataMax == null) {
        throw new Exception("Defina uma data valida.");
      }

      return aluguelDAO.buscarPorClienteEIntervaloDeDias(dataMin, dataMax, aluguel);
    } catch (Exception erro) {
      System.err.println(erro);
      return null;
    }
  }

  public List<AluguelVO> buscarPorDevolucao() throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();
      List<AluguelVO> alugueis = new ArrayList<AluguelVO>();

      ResultSet resposta = aluguelDAO.buscarPorDevolucao();

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

  public void editar(AluguelVO aluguel) throws SQLException {
    try {
      AluguelDAO aluguelDAO = new AluguelDAO();

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

    return documento;
  }

  public void gerarFaturaMensal(LocalDate dataInicial, LocalDate dataFinal)
      throws DocumentException, SQLException, IOException, NullPointerException {
    List<AluguelVO> alugueis = this.buscarPorIntervaloDeDias(dataInicial, dataFinal);
    Double valorTotal = 0.0;

    LocalDateTime dataHoraAtual = LocalDateTime.now();
    DateTimeFormatter formatadorNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    DateTimeFormatter formatadorMesFatura = DateTimeFormatter.ofPattern("MM-yyyy");
    Document relatorio = gerarPDF(
        "Fatura-" + dataInicial.format(formatadorMesFatura) + "-Bibly" + dataHoraAtual.format(formatadorNomeArquivo));

    relatorio.add(Chunk.NEWLINE);

    relatorio.add(new Paragraph("Fatura referente ao mês: " + dataInicial.format(formatadorMesFatura)));

    PdfPTable tabela = new PdfPTable(new float[] { 2f, 8f, 5f, 3f });

    PdfPCell celulaAID = new PdfPCell(new Phrase("Aluguel ID"));
    celulaAID.setHorizontalAlignment(Element.ALIGN_CENTER);

    PdfPCell celulaNome = new PdfPCell(new Phrase("Cliente"));
    celulaAID.setHorizontalAlignment(Element.ALIGN_CENTER);

    PdfPCell celulaCpf = new PdfPCell(new Phrase("CPF"));
    celulaAID.setHorizontalAlignment(Element.ALIGN_CENTER);

    PdfPCell celulaValor = new PdfPCell(new Phrase("Valor"));
    celulaValor.setHorizontalAlignment(Element.ALIGN_CENTER);

    tabela.addCell(celulaAID);
    tabela.addCell(celulaNome);
    tabela.addCell(celulaCpf);
    tabela.addCell(celulaValor);

    Integer totalDeAlugueis = alugueis.size();

    if (totalDeAlugueis <= 0) {
      relatorio.close();
      return;
    }

    for (AluguelVO aluguel : alugueis) {
      valorTotal += aluguel.getValor();
      PdfPCell celula1 = new PdfPCell(new Phrase("" + aluguel.getId()));
      PdfPCell celula2 = new PdfPCell(new Phrase(aluguel.getCliente().getNome()));
      PdfPCell celula3 = new PdfPCell(new Phrase(aluguel.getCliente().getCpf()));
      PdfPCell celula4 = new PdfPCell(new Phrase(formater.formatarValor(aluguel.getValor())));

      tabela.addCell(celula1);
      tabela.addCell(celula2);
      tabela.addCell(celula3);
      tabela.addCell(celula4);
    }
    PdfPCell celula1 = new PdfPCell(new Phrase("Total:"));
    PdfPCell celula2 = new PdfPCell(new Phrase(""));
    PdfPCell celula3 = new PdfPCell(new Phrase(""));
    PdfPCell celula4 = new PdfPCell(new Phrase(formater.formatarValor(valorTotal)));

    tabela.addCell(celula1);
    tabela.addCell(celula2);
    tabela.addCell(celula3);
    tabela.addCell(celula4);

    relatorio.add(tabela);

    relatorio.close();
  }

  public void gerarRelatorioGeral(LocalDate dataInicial, LocalDate dataFinal)
      throws DocumentException, SQLException, IOException, NullPointerException {
    List<AluguelVO> alugueis;
    if (dataInicial != null && dataFinal != null) {
      alugueis = this.buscarPorIntervaloDeDias(dataInicial, dataFinal);
    } else {
      alugueis = this.buscarTodos();
    }

    LocalDateTime dataHoraAtual = LocalDateTime.now();
    DateTimeFormatter formatadorNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    DateTimeFormatter formatadorDataAluguel = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Document relatorio = gerarPDF("RelatorioGeral-Bibly" + dataHoraAtual.format(formatadorNomeArquivo));

    Integer totalDeAlugueis = alugueis.size();

    if (totalDeAlugueis <= 0) {
      relatorio.close();
      return;
    }

    for (AluguelVO aluguel : alugueis) {
      relatorio.add(Chunk.NEWLINE);
      relatorio.add(new Paragraph("ID do aluguel-" + aluguel.getId()));
      relatorio.add(new Paragraph("  -Valor total: " + formater.formatarValor(aluguel.getValor())));
      relatorio.add(new Paragraph("  -Data: " + aluguel.getData().format(formatadorDataAluguel)));
    }

    relatorio.close();
  }

  public void gerarRelatorioPorCliente(LocalDate dataInicial, LocalDate dataFinal, AluguelVO aluguelCliente)
      throws DocumentException, SQLException, IOException, NullPointerException {
    List<AluguelVO> alugueis;
    if (dataInicial != null && dataFinal != null) {
      alugueis = this.buscarPorClienteEIntervaloDeDias(dataInicial, dataFinal, aluguelCliente);
    } else {
      alugueis = this.buscarPorCliente(aluguelCliente);
    }

    LocalDateTime dataHoraAtual = LocalDateTime.now();
    DateTimeFormatter formatadorNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    DateTimeFormatter formatadorDataAluguel = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Document relatorio = gerarPDF("RelatorioPorCliente-Bibly" + aluguelCliente.getCliente().getCpf()
        + dataHoraAtual.format(formatadorNomeArquivo));

    Integer totalDeAlugueis = alugueis.size();

    if (totalDeAlugueis <= 0) {
      relatorio.close();
      return;
    }
    relatorio.add(new Paragraph("Cliente:" + aluguelCliente.getCliente().getNome()));
    relatorio.add(new Paragraph("  CPF: " + aluguelCliente.getCliente().getCpf()));
    relatorio.add(new Paragraph("  Endereço: " + aluguelCliente.getCliente().getEndereco()));
    relatorio.add(new Paragraph("  Número: " + aluguelCliente.getCliente().getCelular()));
    relatorio.add(Chunk.NEWLINE);

    for (AluguelVO aluguel : alugueis) {
      relatorio.add(Chunk.NEWLINE);
      relatorio.add(new Paragraph("ID do aluguel-" + aluguel.getId()));
      relatorio.add(new Paragraph("  -Valor total: " + formater.formatarValor(aluguel.getValor())));
      relatorio.add(new Paragraph("  -Data: " + aluguel.getData().format(formatadorDataAluguel)));
    }

    relatorio.close();
  }
}
