package tiposDeNumeros;

import java.math.BigDecimal;

import utilidades.MetodosSimplificadores;

public class Binario implements InterfazConversion{

  String numeroBinario;
  
  // Constantes
  public static final BigDecimal BASE = new BigDecimal("2");

  public Binario(String numeroBinario) {
    this.numeroBinario = numeroBinario;
  }
  
  // Método que comprueba si un número es binario. Estático
  public static boolean isBinario(String numero) {
    boolean resultado = true;
    int numeroDePuntos = 0;

    // Contemplamos negativos como primer paso
    if (numero.charAt(0) == '-') numero = numero.substring(1);
    
    // Pruebo a hacer la conversión digito a dígito del número en binario. Si alguno no corresponde, el resultado pasa a false
    
    for (int i = 0; i < numero.length(); i++) {
      int digitoPosicionI = (int) (numero.charAt(i));
      
      if (digitoPosicionI == 46) numeroDePuntos++;
      
      // Caracteres en ASCII
      if(digitoPosicionI <46 || digitoPosicionI == 47 || digitoPosicionI > 49 || numeroDePuntos > 1) 
        
        resultado = false;
    }
      
    return resultado;
  }
  

  
  // Método de Binario a Binario. Estático.
  public static String toBinario(String numero) {
    return numero;
    
  }
  
  // Método de Binario a Decimal. Estático.
  
  public static String toDecimal(String numero) {
    
    String[] descomponeArray = MetodosSimplificadores.descompone(numero);
    
    // Contemplamos negativos como primer paso. Si lo es, lo volvemos a añadir al final.
    boolean esNegativo = descomponeArray[0] == "1";
    
    // Declaro la parte entera y decimal de la conversión y del numero inicial
    
    BigDecimal parteEntera = new BigDecimal("0");  
    BigDecimal parteDecimal = new BigDecimal("0");
    
    String binarioEntero = descomponeArray[1];

    for (int i = 0; i < binarioEntero.length(); i++) {
      
      // Si el número es un 1, sumamos a la parte entera 2^(indice de la posición calculada con el i del bucle for)
      if (binarioEntero.charAt(i) == '1') {
        int exponente = binarioEntero.length() - 1 - i;
        BigDecimal parteASumar = BASE.pow(exponente);
        parteEntera = parteEntera.add(parteASumar);
      }
      
    }
    
    // Hacemos lo mismo para la parte decimal en caso de que exista, recalculando dependiendo del i
    if (descomponeArray[2] != null) {
      
      String binarioDecimal = descomponeArray[2];
      
      
      for(int i = 0; i < binarioDecimal.length(); i++) {
        
        if (binarioDecimal.charAt(i) == '1') {
          int exponente = i + 1;
          BigDecimal parteASumar = BigDecimal.ONE.divide(BASE.pow(exponente));
          parteDecimal = parteDecimal.add(parteASumar);
          
        }
      }
    }
    
    BigDecimal resultadoBigDecimal = parteEntera.add(parteDecimal);
    
    String resultado;
    if (esNegativo) resultado = '-' + resultadoBigDecimal.toString();
    else resultado = resultadoBigDecimal.toString();
    
    return resultado;
    
  }
  
  // Método de Binario a Hexadecimal. Estático.
  
  public static String toHexadecimal (String numero) {
    
    String[] descomponeArray = MetodosSimplificadores.descompone(numero);
    
    // Contemplamos negativos como primer paso. Si lo es, lo volvemos a añadir al final.
    boolean esNegativo = descomponeArray[0] == "1";
            
    String binarioEntero = descomponeArray[1];
    
    // Mientras el número binario no tenga un número de dígitos múltiplo de 4, iremos sumando ceros a la izquierda para simplificar cálculos
    while(binarioEntero.length() % 4 != 0) {
      
      binarioEntero = "0" + binarioEntero;    
      
    }
  
    // En este momento, hacemos agrupaciones de 4 en 4  
      
    String resultadoParteEntera = "";
    String resultadoParteDecimal = "";
    
    for(int i = 0; i < binarioEntero.length(); i+=4) {
      
      String subparte = binarioEntero.substring(i, i+4);
      
      // Pasamos el valor a entero y comprobamos a qué número o letra corresponde y la añadimos.
      
      int subparteBaseDecimal = (int)(Double.parseDouble(toDecimal(subparte)));
      
      if (subparteBaseDecimal < 10) 
        resultadoParteEntera += String.valueOf(subparteBaseDecimal);
      else if (subparteBaseDecimal == 10) 
        resultadoParteEntera += 'A';
      else if (subparteBaseDecimal == 11) 
        resultadoParteEntera += 'B';
      else if (subparteBaseDecimal == 12) 
        resultadoParteEntera += 'C';
      else if (subparteBaseDecimal == 13) 
        resultadoParteEntera += 'D';
      else if (subparteBaseDecimal == 14) 
        resultadoParteEntera += 'E';
      else 
        resultadoParteEntera += 'F';
      
    }
      
    // Hacemos lo mismo para la parte decimal en caso de que exista
    if (descomponeArray[2] != null) {
      
      String binarioDecimal = descomponeArray[2];  
      
      // Tenemos que tener de nuevo agrupaciones de 4 en la parte decimal, pero se añaden por la derecha
      while(binarioDecimal.length() % 4 != 0) {
        
        binarioDecimal = binarioDecimal + "0";    
        
      }
      
      for(int i = 0; i < binarioDecimal.length(); i+=4) {


        String subparte = binarioDecimal.substring(i, i+4);
        
        // Pasamos el valor a entero y comprobamos a qué número o letra corresponde y la añadimos.
        
        int subparteBaseDecimal = (int)(Double.parseDouble(toDecimal(subparte)));
        
        if (subparteBaseDecimal < 10) 
          resultadoParteDecimal += String.valueOf(subparteBaseDecimal);
        else if (subparteBaseDecimal == 10) 
          resultadoParteDecimal += 'A';
        else if (subparteBaseDecimal == 11) 
          resultadoParteDecimal += 'B';
        else if (subparteBaseDecimal == 12) 
          resultadoParteDecimal += 'C';
        else if (subparteBaseDecimal == 13) 
          resultadoParteDecimal += 'D';
        else if (subparteBaseDecimal == 14) 
          resultadoParteDecimal += 'E';
        else 
          resultadoParteEntera += 'F';
        
      }
      
    }
    
    // En este punto ya tenemos todos los valores asignados de la parte entera y decimal y volvemos a añadir el símbolo y el punto
    
    if(esNegativo) 
      resultadoParteEntera = '-' + resultadoParteEntera;
    
    if(!resultadoParteDecimal.isEmpty()) resultadoParteEntera += '.';
      
    String resultado  = resultadoParteEntera + resultadoParteDecimal;
    
    return resultado;
      
  }
    
  
  // Método de Binario a Octal. Estático.
  
  public static String toOctal (String numero) {
    
    String[] descomponeArray = MetodosSimplificadores.descompone(numero);
    
    // Contemplamos negativos como primer paso. Si lo es, lo volvemos a añadir al final.
    boolean esNegativo = descomponeArray[0] == "1";
            
    String binarioEntero = descomponeArray[1];
    
    // Mientras el número binario no tenga un número de dígitos múltiplo de 3, iremos sumando ceros a la izquierda para simplificar cálculos
    while(binarioEntero.length() % 3 != 0) {
      
      binarioEntero = "0" + binarioEntero;    
      
    }
  
    // En este momento, hacemos agrupaciones de 3 en 3 
      
    String resultadoParteEntera = "";
    String resultadoParteDecimal = "";
    
    for(int i = 0; i < binarioEntero.length(); i+=3) {
      
      String subparte = binarioEntero.substring(i, i+3);
      
      // Pasamos el valor a entero y la añadimos.
      
      int subparteBaseDecimal = (int)(Double.parseDouble(toDecimal(subparte)));
      
      resultadoParteEntera += String.valueOf(subparteBaseDecimal);

    }
    
    
    // Hacemos lo mismo para la parte decimal en caso de que exista
    if (descomponeArray[2] != null) {
      
      String binarioDecimal = descomponeArray[2];  
      
      // Tenemos que tener de nuevo agrupaciones de 3 en la parte decimal, pero se añaden por la derecha
      while(binarioDecimal.length() % 3 != 0) {
        
        binarioDecimal = binarioDecimal + "0";    
        
      }
      
      for(int i = 0; i < binarioDecimal.length(); i+=3) {


        String subparte = binarioDecimal.substring(i, i+3);
        
        // Pasamos el valor a entero y lo añadimos.
        
        int subparteBaseDecimal = (int)(Double.parseDouble(toDecimal(subparte)));
        

        resultadoParteDecimal += String.valueOf(subparteBaseDecimal);

      }
    }
    
    // En este punto ya tenemos todos los valores asignados de la parte entera y decimal y volvemos a añadir el símbolo y el punto
    
    if(esNegativo) 
      resultadoParteEntera = '-' + resultadoParteEntera;
    
    if(!resultadoParteDecimal.isEmpty()) resultadoParteEntera += '.';
      
    String resultado  = resultadoParteEntera + resultadoParteDecimal;
    
    return resultado;
    

  }
  
  
}
