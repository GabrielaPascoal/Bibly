package model.VO;

public class LivroVO {
  private int id;
  private String titulo;
  private String autor;
  private String genero;
  private int ano;
  private int paginas;
  private double valor;
  private int quantidade;

  // implementação para garantir integridade dos dados

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    try {
      if (id <= 0) {
        throw new Exception("O id do livro não pode ser menor que 0.");
      }

      this.id = id;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String getTitulo() {
    return this.titulo;
  }

  public void setTitulo(String titulo) {
    try {
      if ((titulo == null) || (titulo.equals(""))) {
        throw new Exception("O livro deve conter um tï¿½tulo.");
      }

      this.titulo = titulo;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String getAutor() {
    return this.autor;
  }

  public void setAutor(String autor) {
    try {
      if ((autor == null) || (autor.equals(""))) {
        throw new Exception("O livro deve conter um autor.");
      }

      this.autor = autor;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String getGenero() {
    return this.genero;
  }

  public void setGenero(String estilo) {
    try {
      if ((estilo == null) || (estilo.equals(""))) {
        throw new Exception("O livro deve possuir um estilo.");
      }

      this.genero = estilo;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public int getAno() {
    return this.ano;
  }

  public void setAno(int ano) {
    try {

      if ((String.valueOf(ano).length() != 4)) {
        throw new Exception("O ano deve possuir 4 caractÃ©res.");
      }

      if (ano <= 0) {
        throw new Exception("O ano do livro não pode ser menor que 0.");
      }

      this.ano = ano;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public int getPaginas() {
    return this.paginas;
  }

  public void setPagina(int paginas) {
    try {
      if (paginas <= 0) {
        throw new Exception("A quantidade de paginas do livro não pode ser menor que 0.");
      }

      this.paginas = paginas;

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
        throw new Exception("O valor do livro não pode ser menor que 0.");
      }

      this.valor = valor;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public int getQuantidade() {
    return this.quantidade;
  }

  public void setQuantidade(int quantidade) {
    try {
      if (quantidade < 0) {
        throw new Exception("A quantidade de livros não pode ser menor que 0.");
      }

      this.quantidade = quantidade;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String toString() {
    String obj = "";

    obj = "id: " + this.id + '\n';
    obj += "titulo: " + this.titulo + '\n';
    obj += "autor: " + this.autor + '\n';
    obj += "estilo: " + this.genero + '\n';
    obj += "ano: " + this.ano + '\n';
    obj += "paginas: " + this.paginas + '\n';
    obj += "valor: " + this.valor + '\n';
    obj += "quantidade: " + this.quantidade;

    return obj;
  }
}