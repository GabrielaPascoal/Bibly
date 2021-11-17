package utils;

public class formater {
  public static String formatarValor(Double valor) {
    Double valorArredondado = Math.round(valor * 100.00) / 100.00;
    int numerosVerificados = valorArredondado.toString().split(".")[1].length();
    String zeros = (numerosVerificados == 1) ? "0" : "";

    return ("R$ " + valor.toString().replace('.', ',') + zeros);
  }
}
