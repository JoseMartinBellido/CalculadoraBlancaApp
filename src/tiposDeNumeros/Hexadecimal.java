package tiposDeNumeros;

import utilidades.MetodosSimplificadores;

public class Hexadecimal implements InterfazConversion {

  // Datos de clase
  private String numeroHexadecimal;

  // Constructor
  public Hexadecimal(String numeroHexadecimal) {
    this.numeroHexadecimal = numeroHexadecimal;
  }
  
  // Getter
  public String getNumeroHexadecimal() {
    return numeroHexadecimal;
  }



  // Método que comprueba si un número es hexadecimal. Estático
  public static boolean isHexadecimal(String numero) {
    
    // Contemplamos negativos como primer paso
    if (numero.charAt(0) == '-') numero = numero.substring(1);
    
    // El método toUpperCase no afecta a números pero sí a letras
    numero = numero.toUpperCase();
    boolean resultado = true;
    int numeroDePuntos = 0;
    
    // Pruebo a hacer la conversión digito a dígito del número en hexadecimal. Si alguno no corresponde, el resultado pasa a false
    for (int i = 0; i < numero.length(); i++) {
      int digitoPosicionI = (int) (numero.charAt(i));
      
      // Compruebo si el dígito es un punto
      if (digitoPosicionI == 46) numeroDePuntos++;
        
      // Caracteres en ASCII. Solo quedan letras mayúsculas
      if(digitoPosicionI <46 || digitoPosicionI == 47 || (digitoPosicionI > 57 && digitoPosicionI < 65) 
          || digitoPosicionI > 70 || numeroDePuntos > 1) 
        
        resultado = false;
    }
      
    return resultado;
  }
  
  
  // Método de Hexadecimal a Hexadecimal. Estático.
  
  public static String toHexadecimal (String numero) {
    return numero;
  }
  
  // Método de Hexadecimal a Binario. Estático. Reutilización de código de decimal a binario.
  
  public static String toBinario (String numero) {
    
    String[] descomponeArray = MetodosSimplificadores.descompone(numero);
    
    // Contemplamos negativos como primer paso. Si lo es, lo volvemos a añadir al final.
    boolean esNegativo = descomponeArray[0] == "1";
    
    // Para realizar la conversión, vamos a convertir manualmente cada número en un conjunto de 4 binarios para añadirlos posteriormente.
    // Entonces, la conversión trata, básicamente, de convertir cada valor por separado.
    
    String parteHexadecimalEntera = descomponeArray[1];
    String parteBinariaEntera = "";
    String parteBinariaDecimal = "";
    
    for (int i = 0; i < parteHexadecimalEntera.length(); i++) {
      
      String valorCaracterI = String.valueOf(parteHexadecimalEntera.charAt(i));
      int valorBaseDecimalCaracterI;
      
      // Asignamos un valor entero del 1 al 15 a cada valor hexadecimal y éste lo pasamos a binario para sumarlo a nuestra parte entera.
      
      if(valorCaracterI.equals("A"))
        valorBaseDecimalCaracterI = 10;
      else if(valorCaracterI.equals("B"))
        valorBaseDecimalCaracterI = 11;
      else if(valorCaracterI.equals("C"))
        valorBaseDecimalCaracterI = 12;
      else if(valorCaracterI.equals("D"))
        valorBaseDecimalCaracterI = 13;
      else if(valorCaracterI.equals("E"))
        valorBaseDecimalCaracterI = 14;
      else if(valorCaracterI.equals("F"))
        valorBaseDecimalCaracterI = 15;
      else 
        valorBaseDecimalCaracterI = Integer.parseInt(valorCaracterI);
      
      
      // Debemos comprobar al hacer el cambio que no perdemos ceros a la izquierda que van a ir intercalados.
      String valorAIngresar = Decimal.toBinario(String.valueOf(valorBaseDecimalCaracterI));
      while (valorAIngresar.length() < 4)
        valorAIngresar = "0" + valorAIngresar;
        
      parteBinariaEntera += valorAIngresar;
      
    }
    
    // Con la parte decimal, si existe, actuamos igual.
    
    if (descomponeArray[2] != null) {
      
      String parteHexadecimalDecimal = descomponeArray[2];
      
      for (int i = 0; i < parteHexadecimalDecimal.length(); i++) {
        
        String valorCaracterI = String.valueOf(parteHexadecimalDecimal.charAt(i));
        int valorBaseDecimalCaracterI;
        
        // Asignamos un valor entero del 1 al 15 a cada valor hexadecimal y éste lo pasamos a binario para sumarlo a nuestra parte entera.
        
        if(valorCaracterI.equals("A"))
          valorBaseDecimalCaracterI = 10;
        else if(valorCaracterI.equals("B"))
          valorBaseDecimalCaracterI = 11;
        else if(valorCaracterI.equals("C"))
          valorBaseDecimalCaracterI = 12;
        else if(valorCaracterI.equals("D"))
          valorBaseDecimalCaracterI = 13;
        else if(valorCaracterI.equals("E"))
          valorBaseDecimalCaracterI = 14;
        else if(valorCaracterI.equals("F"))
          valorBaseDecimalCaracterI = 15;
        else 
          valorBaseDecimalCaracterI = Integer.parseInt(valorCaracterI);
        
        String valorAIngresar = Decimal.toBinario(String.valueOf(valorBaseDecimalCaracterI));
        while (valorAIngresar.length() < 4)
          valorAIngresar = "0" + valorAIngresar;
          
        parteBinariaDecimal += valorAIngresar;
        
      }
      
    }
    
    // De esta forma, ya tenemos las partes entera y decimal de nuestro número hexadecimal pasado a binario.
    
    if (esNegativo) 
      parteBinariaEntera = '-' + parteBinariaEntera;
    
    if (!parteBinariaDecimal.isEmpty()) 
      parteBinariaEntera += '.';
    
    String resultadoBinario = parteBinariaEntera + parteBinariaDecimal;
    
    return resultadoBinario;
    
  }
  
  //Método de hexadecimal a Decimal. Estático. Reutilización de código.
    
   public static String toDecimal (String numero) {
     return Binario.toDecimal(toBinario(numero));
   }
   
   // Método de hexadecimal a octal. Estático. Reutilización de código.
   
   public static String toOctal (String numero) {
     return Binario.toOctal(toBinario(numero));
   }
   
  
  
}
