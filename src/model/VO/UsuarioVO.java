package model.VO;

public class UsuarioVO {
  private int id;
  private String cpf;
  private String senha;
  
  // implementação para garantir integridade dos dados

  public int getInt() {
    return this.id;
  }

  public void setInt(int id) {
    try {
      if (id <= 0) {
        throw new Exception("O id do usuário não pode ser menor que 0.");
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
        throw new Exception("O usuário deve informar um CPF.");
      }

      if (cpf.length() != 11) {
        throw new Exception("o usuário deve informar um CPF válido.");
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
        throw new Exception("O usuário deve apresentar uma senha.");
      }

      this.senha = senha;

    } catch (Exception erro) {
    	
    	System.err.println(erro);

    }
  }
}