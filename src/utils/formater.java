package utils;

public class formater {
  public static String formatarValor(Double valor) {
    int casasDecimais = valor.toString().split("\\.")[1].length();
    String zeros = (casasDecimais == 1) ? "0" : "";
    return ("R$ " + valor.toString().replace('.', ',') + zeros);
  }
}
