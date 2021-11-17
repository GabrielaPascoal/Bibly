package model.VO;

public interface ProdutoInterVO {
  int id = 0;
  String titulo = "ninguem";
  int quantidade = 0;
  double valor = 0;

  public int getId();

  public void setId(int id);

  public String getTitulo();

  public void setTitulo(String titulo);

  public int getQuantidade();

  public void setQuantidade(int quantidade);

  public double getValor();

  public void setValor(double valor);
}
