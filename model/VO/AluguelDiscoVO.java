package model.VO;

public class AluguelDiscoVO<T> {
  private int id;
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