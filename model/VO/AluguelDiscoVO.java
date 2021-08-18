package model.VO;

public class AluguelDiscoVO {
  private int id;
  private ClienteVO cliente;
  private DiscoVO produto;
  private int quantidade;
  private double valor;

  public int getInt() {
    return this.id;
  }

  public void setInt(int id) {
    try {
      if (id <= 0) {
        throw new Exception("O id do aluguel não pode ser menor que 0.");
      }

      this.id = id;

    } catch (Exception erro) {

    }
  }

  public ClienteVO getCliente() {
    return this.cliente;
  }

  public void setCliente(ClienteVO cliente) {
    try {
      if (cliente == null) {
        throw new Exception("O Cliente que irá alugar deve ser informar.");
      }

      this.cliente = cliente;

    } catch (Exception erro) {

    }
  }

  public DiscoVO getProduto() {
    return this.produto;
  }

  public void setProduto(DiscoVO produto) {
    try {
      if (produto == null) {
        throw new Exception("O disco que irá ser alugado deve ser informar.");
      }

      this.produto = produto;

    } catch (Exception erro) {

    }
  }

  public double getValor() {
    return this.valor;
  }

  public void setValor(double valor) {
    try {
      if (valor <= 0) {
        throw new Exception("O valor do aluguel não pode ser menor que 0.");
      }

      this.valor = valor;

    } catch (Exception erro) {

    }
  }

  public int getQuantidade() {
    return this.quantidade;
  }

  public void setQuantidade(int quantidade) {
    try {
      if (quantidade <= 0) {
        throw new Exception("A quantidade itens alugados não pode ser menor que 0.");
      }

      this.quantidade = quantidade;

    } catch (Exception erro) {

    }
  }
}