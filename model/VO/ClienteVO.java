package model.VO;

public class ClienteVO {
  private int id;
  private String nome;
  private String cpf;
  private String endereco;
  private int celular;

  public int getInt() {
    return this.id;
  }

  public void setInt(int id) {
    try {
      if (id <= 0) {
        throw new Exception("O id do cliente não pode ser menor que 0.");
      }

      this.id = id;

    } catch (Exception erro) {

    }
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    try {
      if ((nome == null) || (nome == (""))) {
        throw new Exception("O cliente deve apresentar um nome.");
      }

      this.nome = nome;

    } catch (Exception erro) {

    }
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    try {
      if ((cpf == null) || (cpf == (""))) {
        throw new Exception("O cliente deve informar um CPF.");
      }

      if (cpf.length() != 11) {
        throw new Exception("O cliente deve informar um CPF válido.");
      }

      this.cpf = cpf;

    } catch (Exception erro) {

    }
  }

  public String getEndereco() {
    return this.endereco;
  }

  public void setEndereco(String endereco) {
    try {
      if ((endereco == null) || (endereco == (""))) {
        throw new Exception("O cliente deve informar um endereco.");
      }

      this.endereco = endereco;

    } catch (Exception erro) {

    }
  }

  public int getCelular() {
    return this.celular;
  }

  public void setCelular(int celular) {
    try {
      if (String.valueOf(celular).length() <= 8) {
        throw new Exception("O cliente deve informar um número válido.");
      }

      this.celular = celular;

    } catch (Exception erro) {

    }
  }
}