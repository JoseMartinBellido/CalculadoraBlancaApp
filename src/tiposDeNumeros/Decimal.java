package tiposDeNumeros;

import utilidades.MetodosSimplificadores;

public class Decimal {

  String numeroDecimal;

  public Decimal(String numeroDecimal) {
    this.numeroDecimal = numeroDecimal;
  }
  
  // Getter
  
  public String getNumeroDecimal() {
    return numeroDecimal;
  }



  // Método que comprueba si un número es decimal. Estático
  public static boolean isDecimal(String numero) {
    boolean resultado = true;
    Double numeroDouble = null;
    
    // Pruebo a pasar el número a Double. Si se puede hacer, significa que el número es decimal, y sino, seguirá siendo null.
    try {
      numeroDouble = Double.valueOf(numero);
      
    }catch (NumberFormatException e) {
    }
    
    if(numeroDouble == null) resultado = false;
    
    return resultado;
  }
  
  // Método decimal a decimal. Estático.
  
  public static String decimalToDecimal (String numero) {
    
    return numero;
  }
  
  
  // Método decimal a binario. Estático.
  
  public static String decimalToBinario (String numero) {
    
    String[] descomponeArray = MetodosSimplificadores.descompone(numero);
    
    // Contemplamos negativos como primer paso. Si lo es, lo volvemos a añadir al final.
    boolean esNegativo = descomponeArray[0] == "1";
    
    // Una vez introducimos el número, tenemos por seguro que va a tener formato en base 10. 
    // Con lo cual, hacer lo siguiente no puede dar error.
    
    Long parteEnteraBaseDecimal = Long.parseLong(descomponeArray[1]);
    String parteEnteraBinaria = "";
    String parteDecimalBinaria = "";
    
    while (parteEnteraBaseDecimal > 1) {
      
      // División entre enteros hecha para obtener cociente y resto sin decimales (int)
      parteEnteraBinaria = String.valueOf(parteEnteraBaseDecimal % 2) + parteEnteraBinaria;
      parteEnteraBaseDecimal /= 2;

    }
    
    parteEnteraBinaria = String.valueOf(parteEnteraBaseDecimal) + parteEnteraBinaria;
    
    // Hacemos lo mismo con la parte decimal en caso de que la haya. El calculo ahora es al revés, multiplicamos por 2 y nos quedamos
    // con la parte entera. Lo aproximamos a 12 decimales para tener 3 y 4 completos en las conversiones a hexadecimal y octal respectiv.
    
    if (descomponeArray[2] != null) {
      
      Double parteDecimalBaseDecimal = Double.parseDouble("0." + descomponeArray[2]);
      
      for(int i = 0; i < 13; i++) {
        
        int numeroAAgregar = (int) (parteDecimalBaseDecimal * 2);
        
        parteDecimalBaseDecimal = (parteDecimalBaseDecimal * 2) - numeroAAgregar;
        
        parteDecimalBinaria += String.valueOf(numeroAAgregar);
        
      }
      
    }
    
    if (esNegativo) 
      parteEnteraBinaria = '-' + parteEnteraBinaria;
    
    if (parteDecimalBinaria != "") 
      parteEnteraBinaria += '.';
    
    String resultadoBinario = parteEnteraBinaria + parteDecimalBinaria;
    
    return resultadoBinario;
    
    
  }
  
  // Método decimal a octal. Estático. Reutilizamos código
  
  public static String decimalToOctal (String numero) {
    
    return Binario.binarioToOctal(decimalToBinario(numero));
    
  }
  
  // Método decimal a Hexadecimal. Estático. Reutilizamos código
  
  public static String decimalToHexadecimal (String numero) {
    
    return Binario.binarioToHexadecimal(decimalToBinario(numero));
    
  }
  
  // -------------------------------------------- Operaciones ----------------------
  
  // Suma - Convertimos a double y sumamos, para volver a dar un String
  
  public String suma(Decimal decimal) {
    double numeroBaseDecimal = Double.parseDouble(numeroDecimal);
    double numero2BaseDecimal = Double.parseDouble(decimal.getNumeroDecimal());
    
    double suma = numeroBaseDecimal + numero2BaseDecimal;
    return String.valueOf(suma);

  }
  
  // Al primero le resta el segundo
  public String resta(Decimal decimal) {
    double numeroBaseDecimal = Double.parseDouble(numeroDecimal);
    double numero2BaseDecimal = Double.parseDouble(decimal.getNumeroDecimal());
    
    double resta = numeroBaseDecimal - numero2BaseDecimal;
    return String.valueOf(resta);

  }
  
  // Producto
  public String producto(Decimal decimal) {
    double numeroBaseDecimal = Double.parseDouble(numeroDecimal);
    double numero2BaseDecimal = Double.parseDouble(decimal.getNumeroDecimal());
    
    double producto = numeroBaseDecimal * numero2BaseDecimal;
    return String.valueOf(producto);

  }
  
  // División. El primero lo divide entre el segundo
  public String division(Decimal decimal) {
    double numeroBaseDecimal = Double.parseDouble(numeroDecimal);
    double numero2BaseDecimal = Double.parseDouble(decimal.getNumeroDecimal());
    
    double division = numeroBaseDecimal / numero2BaseDecimal;
    return String.valueOf(division);

  } 
  
}
