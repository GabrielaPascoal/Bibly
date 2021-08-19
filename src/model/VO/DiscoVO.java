package model.VO;

public class DiscoVO {
  private int id;
  private String titulo;
  private String artista;
  private String estilo;
  private double valor;
  private int quantidade;

  public int getInt() {
    return this.id;
  }

  public void setInt(int id) {
    try {
      if (id <= 0) {
        throw new Exception("O id do disco não pode ser menor que 0.");
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
        throw new Exception("O disco deve conter um título.");
      }

      this.titulo = titulo;

    } catch (Exception erro) {
    	
    	System.err.println(erro);

    }
  }

  public String getArtista() {
    return this.artista;
  }

  public void setArtista(String artista) {
    try {
      if ((artista == null) || (artista.equals(""))) {
        throw new Exception("O disco deve conter um artítsa.");
      }

      this.artista = artista;

    } catch (Exception erro) {
    	
    	System.err.println(erro);

    }
  }

  public String getEstilo() {
    return this.estilo;
  }

  public void setEstilo(String estilo) {
    try {
      if ((estilo == null) || (estilo.equals(""))) {
        throw new Exception("O disco deve possuir um estilo.");
      }

      this.estilo = estilo;

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
        throw new Exception("O valor do disco n�o pode ser menor que 0.");
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
        throw new Exception("A quantidade de discos não pode ser menor que 0.");
      }

      this.quantidade = quantidade;

    } catch (Exception erro) {
    	
    	System.err.println(erro);

    }
  }
}