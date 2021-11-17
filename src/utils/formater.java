package utils;

public class formater {
  public static String formatarValor(Double valor) {
    Double valorArredondado = Math.round(valor * 100.00) / 100.00;
    if (valorArredondado.toString().split(".").length == 2) {
      int casasDecimais = valorArredondado.toString().split(".")[1].length();
      String zeros = (casasDecimais == 1) ? "0" : "";
      return ("R$ " + valor.toString().replace('.', ',') + zeros);
    }

    return ("R$ " + valor.toString().replace('.', ',') + "00");
  }
}
