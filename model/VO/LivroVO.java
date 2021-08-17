package model.VO;

public class LivroVO {
  private Integer id;
  private String titulo;
  private String artista;
  private String estilo;
  private Integer ano;
  private Integer paginas;
  private Double valor;
  private Integer quantidade;

  public Integer getInt() {
    return this.id;
  }

  public void setInt(Integer id) {
    try {
      if (id.equals(null)) {
        throw new Exception("O livro deve possuir um id.");
      }

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

  public Integer getAno() {
    return this.ano;
  }

  public void setAno(Integer ano) {
    try {
      if (ano.equals(null)) {
        throw new Exception("O livro deve possuir um ano de lançamento.");
      }

      if (ano <= 0) {
        throw new Exception("O ano do livro não pode ser menor que 0.");
      }

      this.ano = ano;

    } catch (Exception erro) {

    }
  }

  public Integer getPagina() {
    return this.paginas;
  }

  public void setPagina(Integer paginas) {
    try {
      if ((paginas.equals(null)) || (paginas == 0)) {
        throw new Exception("O livro deve possuir algumas paginas.");
      }

      if (paginas <= 0) {
        throw new Exception("A quantidade de paginas do livro não pode ser menor que 0.");
      }

      this.paginas = paginas;

    } catch (Exception erro) {

    }
  }

  public Double getValor() {
    return this.valor;
  }

  public void setValor(Double valor) {
    try {
      if (valor.equals(null)) {
        throw new Exception("O livro deve possuir um valor.");
      }

      if (valor <= 0) {
        throw new Exception("O valor do livro não pode ser menor que 0.");
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
        throw new Exception("Deve haver uma quantidade de livros.");
      }

      if (quantidade <= 0) {
        throw new Exception("A quantidade de livros não pode ser menor que 0.");
      }

      this.quantidade = quantidade;

    } catch (Exception erro) {

    }
  }
}