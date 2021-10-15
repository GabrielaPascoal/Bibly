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
    
      this.cpf = cpf;

    
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
   

      this.senha = senha;

   
  }

  public String toString() {
    String obj = "";

    obj = "id: " + this.id + '\n';
    obj += "cpf: " + this.cpf + '\n';

    return obj;
  }
}