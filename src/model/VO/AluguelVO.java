package model.VO;

import java.time.LocalDate;

public class AluguelVO {
  private int id;
  private double valor;
  private ClienteVO cliente;
  private AluguelLivroVO[] livros;
  private AluguelDiscoVO[] discos;
  private LocalDate data;

  public ClienteVO getCliente() {
    return this.cliente;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    try {
      if (id <= 0) {
        throw new Exception("O id do aluguel n�o pode ser menor que 0.");
      }

      this.id = id;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public void setCliente(ClienteVO cliente) {
    try {
      if (cliente == null) {
        throw new Exception("O Cliente que ir� alugar deve ser informar.");
      }

      this.cliente = cliente;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public double getValor() {
    return this.valor;
  }

  public void setValor(double valor) {
    try {
      if (valor <= 0) {
        throw new Exception("O valor do aluguel n�o pode ser menor que 0.");
      }

      this.valor = valor;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public AluguelLivroVO[] getLivros() {
    return this.livros;
  }

  public void setLivros(AluguelLivroVO[] livros) {
    try {
      for (AluguelLivroVO livro : livros) {
        if (livro == null) {
          throw new Exception("Nenhum livro da lista pode ser nulo.");
        }
      }

      this.livros = livros;
    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public AluguelDiscoVO[] getDiscos() {
    return this.discos;
  }

  public void setDiscos(AluguelDiscoVO[] discos) {
    try {
      for (AluguelDiscoVO disco : discos) {
        if (disco == null) {
          throw new Exception("Nenhum disco da lista pode ser nulo.");
        }
      }

      this.discos = discos;
    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public LocalDate getData() {
    return this.data;
  }

  public void setData(LocalDate data) {
    try {
      if (data == null) {
        throw new Exception("A data do aluguel deve ser informado.");
      }

      this.data = data;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String toString() {
    String obj = "";

    obj = "id: " + this.id + '\n';
    obj += "valor: " + this.valor + '\n';
    obj += "data: " + this.data;

    return obj;
  }
}