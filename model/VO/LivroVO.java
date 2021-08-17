package model.VO;

public class LivroVO {
  private int id;
  private String titulo;
  private String artista;
  private String estilo;
  private int ano;
  private int paginas;
  private double valor;
  private int quantidade;

  public int getInt() {
    return this.id;
  }

  public void setInt(int id) {
    try {
      if (id <= 0) {
        throw new Exception("O id do livro não pode ser menor que 0.");
      }

      this.id = id;

    } catch (Exception erro) {

    }
  }

  public String getTitulo() {
    return this.titulo;
  }

  public void setTitulo(String titulo) {
    try {
      if ((titulo == null) || (titulo == (""))) {
        throw new Exception("O livro deve conter um título.");
      }

      this.titulo = titulo;

    } catch (Exception erro) {

    }
  }

  public String getArtista() {
    return this.artista;
  }

  public void setArtista(String artista) {
    try {
      if ((artista == null) || (artista == (""))) {
        throw new Exception("O livro deve conter um artítsa.");
      }

      this.artista = artista;

    } catch (Exception erro) {

    }
  }

  public String getEstilo() {
    return this.estilo;
  }

  public void setEstilo(String estilo) {
    try {
      if ((estilo == null) || (estilo == (""))) {
        throw new Exception("O livro deve possuir um estilo.");
      }

      this.estilo = estilo;

    } catch (Exception erro) {

    }
  }

  public int getAno() {
    return this.ano;
  }

  public void setAno(int ano) {
    try {
      if (ano <= 0) {
        throw new Exception("O ano do livro não pode ser menor que 0.");
      }

      this.ano = ano;

    } catch (Exception erro) {

    }
  }

  public int getPagina() {
    return this.paginas;
  }

  public void setPagina(int paginas) {
    try {
      if (paginas <= 0) {
        throw new Exception("A quantidade de paginas do livro não pode ser menor que 0.");
      }

      this.paginas = paginas;

    } catch (Exception erro) {

    }
  }

  public double getValor() {
    return this.valor;
  }

  public void setValor(double valor) {
    try {
      if (valor <= 0) {
        throw new Exception("O valor do livro não pode ser menor que 0.");
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
        throw new Exception("A quantidade de livros não pode ser menor que 0.");
      }

      this.quantidade = quantidade;

    } catch (Exception erro) {

    }
  }
}