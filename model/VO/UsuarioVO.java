package model.VO;

public class UsuarioVO {
  private Integer id;
  private String cpf;
  private String senha;

  public Integer getInt() {
    return this.id;
  }

  public void setInt(Integer id) {
    try {
      if (id.equals(null)) {
        throw new Exception("O usuário deve possuir um id.");
      }

      if (id <= 0) {
        throw new Exception("O id do usuário não pode ser menor que 0.");
      }

      this.id = id;

    } catch (Exception erro) {

    }
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    try {
      if ((cpf == null) || (cpf == (""))) {
        throw new Exception("O usuário deve informar um CPF.");
      }

      if (cpf.length() != 11) {
        throw new Exception("o usuário deve informar um CPF válido.");
      }

      this.cpf = cpf;

    } catch (Exception erro) {

    }
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    try {
      if ((senha == null) || (senha == (""))) {
        throw new Exception("O usuário deve apresentar uma senha.");
      }

      this.senha = senha;

    } catch (Exception erro) {

    }
  }
}