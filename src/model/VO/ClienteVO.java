package model.VO;

public class ClienteVO {
  private int id;
  private String nome;
  private String cpf;
  private String endereco;
  private String celular;

  // implementa��o para garantir integridade dos dados

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    try {
      if (id <= 0) {
        throw new Exception("O id do cliente n�o pode ser menor que 0.");
      }

      this.id = id;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    try {
      if ((nome == null) || (nome.equals(""))) {
        throw new Exception("O cliente deve apresentar um nome.");
      }

      this.nome = nome;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
	    
      this.cpf = cpf;

    
  }

  public String getEndereco() {
    return this.endereco;
  }

  public void setEndereco(String endereco) {
    try {
      if ((endereco == null) || (endereco.equals(""))) {
        throw new Exception("O cliente deve informar um endereco.");
      }

      this.endereco = endereco;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String getCelular() {
    return this.celular;
  }

  public void setCelular(String celular) {
    try {
      if (celular.length() <= 8) {
        throw new Exception("O cliente deve informar um n�mero v�lido.");
      }

      this.celular = celular;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String toString() {
    String obj = "";

    obj = "id: " + this.id + '\n';
    obj += "nome: " + this.nome + '\n';
    obj += "cpf: " + this.cpf + '\n';
    obj += "endereco: " + this.endereco + '\n';
    obj += "celular: " + this.celular;

    return obj;
  }
}