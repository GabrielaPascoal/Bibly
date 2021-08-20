
import java.util.Scanner;
import model.VO.AluguelLivroVO;
import model.VO.ClienteVO;
import model.VO.LivroVO;

class Main {
  public static void main(String args[]) {

	  Scanner ler = new Scanner(System.in);
	  LivroVO livro = new LivroVO();
	  
	  System.out.println("Digite o ano do livro: ");
	  int liv = ler.nextInt();
	  livro.setAno(liv);
	  

	 	  
  }
}