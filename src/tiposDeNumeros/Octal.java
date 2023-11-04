package tiposDeNumeros;

import utilidades.MetodosSimplificadores;

public class Octal {
  
  // Datos de clase
  private String numeroOctal;

  // Constructor
  public Octal(String numeroOctal) {
    this.numeroOctal = numeroOctal;
  }
  
  // Getter
  public String getNumeroOctal() {
    return numeroOctal;
  }



  // Método que comprueba si un número es octal. Estático
  public static boolean isOctal(String numero) {
    boolean resultado = true;
    int numeroDePuntos = 0;
    
    // Contemplamos negativos como primer paso
    if (numero.charAt(0) == '-') numero = numero.substring(1);
    
    // Pruebo a hacer la conversión digito a dígito del número en octal. Si alguno no corresponde, el resultado pasa a false
    
    for (int i = 0; i < numero.length(); i++) {
      int digitoPosicionI = (int) (numero.charAt(i));
      
      //Considero el punto como separador entre la parte entera y decimal. Solo puede tener uno. Es el carácter 46 en ASCII
      
      if(digitoPosicionI == 46) numeroDePuntos++;
      
      // Caracteres en ASCII
      if(digitoPosicionI <46 || digitoPosicionI == 47 || digitoPosicionI > 55 || numeroDePuntos > 1) 
        
        resultado = false;
    }
      
    return resultado;
  }
  
  
  // Método de octal a octal. Estático.
  
  public static String octalToOctal (String numero) {
    
    return numero;
  }
  
  // Método de octal a binario. Estático. Reutilización de código de Decimal a binario.
  
  public static String octalToBinario (String numero) {
    
    String[] descomponeArray = MetodosSimplificadores.descompone(numero);
    
    // Contemplamos negativos como primer paso. Si lo es, lo volvemos a añadir al final.
    boolean esNegativo = descomponeArray[0] == "1";
    
    // Para realizar la conversión, vamos a convertir manualmente cada número en un conjunto de 3 binarios para añadirlos posteriormente.
    // Entonces, la conversión trata, básicamente, de convertir cada número como si fuese decimal por separado.
    
    String parteOctalEntera = descomponeArray[1];
    String parteBinariaEntera = "";
    String parteBinariaDecimal = "";
    
    for (int i = 0; i < parteOctalEntera.length(); i++) {
      
      String numeroIndiceI = String.valueOf(parteOctalEntera.charAt(i));
      
      // Debemos comprobar al hacer el cambio que no perdemos ceros a la izquierda que van a ir intercalados.
      String valorAIngresar = Decimal.decimalToBinario(String.valueOf(numeroIndiceI));
      while (valorAIngresar.length() < 3)
        valorAIngresar = "0" + valorAIngresar;
        
      parteBinariaEntera += valorAIngresar;
      
    }
    
    // La conversión de la parte decimal es exáctamente igual
    
    if (descomponeArray[2] != null) {
      
      String parteOctalDecimal = descomponeArray[2];
      
      for (int i = 0; i < parteOctalDecimal.length(); i++) {
        
        String numeroIndiceI = String.valueOf(parteOctalDecimal.charAt(i));
        
        String valorAIngresar = Decimal.decimalToBinario(String.valueOf(numeroIndiceI));
        while (valorAIngresar.length() < 3)
          valorAIngresar = "0" + valorAIngresar;
          
        parteBinariaDecimal += valorAIngresar;
        
      }
      
    }
      
    if (esNegativo) 
      parteBinariaEntera = '-' + parteBinariaEntera;
    
    if (!parteBinariaDecimal.isEmpty()) 
      parteBinariaEntera += '.';
    
    String resultadoBinario = parteBinariaEntera + parteBinariaDecimal;
    
    return resultadoBinario;
  }
  
  // Método de Octal a Decimal. Estático. Reutilización de código.
  
  public static String octalToDecimal (String numero) {
    return Binario.binarioToDecimal(octalToBinario(numero));
  }
  
  // Método de Octal a Hexadecimal. Estático. Reutilización de código.
  
  public static String octalToHexadecimal (String numero) {
    return Binario.binarioToHexadecimal(octalToBinario(numero));
  }
  
  
}
