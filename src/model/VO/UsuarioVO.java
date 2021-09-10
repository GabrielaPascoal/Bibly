package model.VO;

public class UsuarioVO {
  private int id;
  private String cpf;
  private String senha;

  // implementa��o para garantir integridade dos dados

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    try {
      if (id <= 0) {
        throw new Exception("O id do usu�rio n�o pode ser menor que 0.");
      }

      this.id = id;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    try {
      if ((cpf == null) || (cpf.equals(""))) {
        throw new Exception("O usu�rio deve informar um CPF.");
      }

      if (cpf.length() != 11) {
        throw new Exception("o usu�rio deve informar um CPF v�lido.");
      }

      this.cpf = cpf;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    try {
      if ((senha == null) || (senha.equals(""))) {
        throw new Exception("O usu�rio deve apresentar uma senha.");
      }

      this.senha = senha;

    } catch (Exception erro) {

      System.err.println(erro);

    }
  }
}