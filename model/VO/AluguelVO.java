package model.VO;

public class AluguelVO {
  private Integer id;
  private String produto;
  private Integer quantidade;
  private Double valor;

  public Integer getInt() {
    return this.id;
  }

  public void setInt(Integer id) {
    try {
      if (id.equals(null)) {
        throw new Exception("O aluguel deve possuir um id.");
      }

      if (id <= 0) {
        throw new Exception("O id do aluguel não pode ser menor que 0.");
      }

      this.id = id;

    } catch (Exception erro) {

    }
  }

  public String getProduto() {
    return this.produto;
  }

  public void setProduto(String produto) {
    try {
      if ((produto == null) || (produto == (""))) {
        throw new Exception("O cliente deve informar o produto que irá ser alugado.");
      }

      this.produto = produto;

    } catch (Exception erro) {

    }
  }

  public Double getValor() {
    return this.valor;
  }

  public void setValor(Double valor) {
    try {
      if (valor.equals(null)) {
        throw new Exception("O aluguel deve possuir um valor.");
      }

      if (valor <= 0) {
        throw new Exception("O valor do aluguel não pode ser menor que 0.");
      }

      this.valor = valor;

    } catch (Exception erro) {

    }
  }

  public Integer getQuantidade() {
    return this.quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    try {
      if (quantidade.equals(null)) {
        throw new Exception("Deve haver uma quantidade de itens alugados.");
      }

      if (quantidade <= 0) {
        throw new Exception("A quantidade itens alugados não pode ser menor que 0.");
      }

      this.quantidade = quantidade;

    } catch (Exception erro) {

    }
  }
}