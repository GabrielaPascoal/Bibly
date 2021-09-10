package model.VO;

public class AluguelDiscoVO {
  private AluguelVO aluguel;
  private DiscoVO produto;
  private int quantidade;

  // implementa��o para garantir integridade dos dados

  public AluguelVO getAluguel() {
    return this.aluguel;
  }

  public void setAluguel(AluguelVO aluguel) {
    try {
      if (aluguel == null) {
        throw new Exception("O aluguel à ser cadastrado deve ser informado.");
      }

      this.aluguel = aluguel;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public DiscoVO getProduto() {
    return this.produto;
  }

  public void setProduto(DiscoVO produto) {
    try {
      if (produto == null) {
        throw new Exception("O disco que ir� ser alugado deve ser informado.");
      }

      this.produto = produto;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public int getQuantidade() {
    return this.quantidade;
  }

  public void setQuantidade(int quantidade) {
    try {
      if (quantidade <= 0) {
        throw new Exception("A quantidade de itens alugados n�o pode ser menor que 0.");
      }

      this.quantidade = quantidade;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }
}