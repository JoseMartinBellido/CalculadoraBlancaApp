package tiposDeNumeros;

import java.math.BigDecimal;

import utilidades.MetodosSimplificadores;

public class Decimal implements InterfazConversion, InterfazOperaciones{

  String numeroDecimal;
  
  // Constantes
  public static final BigDecimal BASE_BINARIA = new BigDecimal("2");
  
  // Constructor

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
  
  public static String toDecimal (String numero) {
    
    return numero;
  }
  
  
  // Método decimal a binario. Estático.
  
  public static String toBinario (String numero) {
    
    String[] descomponeArray = MetodosSimplificadores.descompone(numero);
    
    // Contemplamos negativos como primer paso. Si lo es, lo volvemos a añadir al final.
    boolean esNegativo = descomponeArray[0] == "1";
    
    // Una vez introducimos el número, tenemos por seguro que va a tener formato en base 10. 
    // Con lo cual, hacer lo siguiente no puede dar error.
    
    BigDecimal parteEnteraBaseDecimal = new BigDecimal(descomponeArray[1]);
    String parteEnteraBinaria = "";
    String parteDecimalBinaria = "";
    
    while (parteEnteraBaseDecimal.compareTo(BigDecimal.ONE) == 1) {
      
      // División entre enteros hecha para obtener cociente y resto sin decimales (int)
      parteEnteraBinaria = parteEnteraBaseDecimal.remainder(BASE_BINARIA).toString() + parteEnteraBinaria;
      parteEnteraBaseDecimal = parteEnteraBaseDecimal.divideToIntegralValue(BASE_BINARIA);

    }
    
    parteEnteraBinaria = String.valueOf(parteEnteraBaseDecimal) + parteEnteraBinaria;
    
    // Hacemos lo mismo con la parte decimal en caso de que la haya. El calculo ahora es al revés, multiplicamos por 2 y nos quedamos
    // con la parte entera. Lo aproximamos a 12 decimales para tener 3 y 4 completos en las conversiones a hexadecimal y octal respectiv.
    
    if (descomponeArray[2] != null) {
      
      BigDecimal parteDecimalBaseDecimal = new BigDecimal("0." + descomponeArray[2]);
      
      for(int i = 0; i < 13; i++) {
        
        int numeroAAgregar = (parteDecimalBaseDecimal.multiply(BASE_BINARIA).intValue());
        
        parteDecimalBaseDecimal = (parteDecimalBaseDecimal.multiply(BASE_BINARIA)).subtract(new BigDecimal(numeroAAgregar));
        
        parteDecimalBinaria += String.valueOf(numeroAAgregar);
        
      }
      
    }
    
    if (esNegativo) 
      parteEnteraBinaria = '-' + parteEnteraBinaria;
    
    if (!parteDecimalBinaria.isEmpty()) 
      parteEnteraBinaria += '.';
    
    String resultadoBinario = parteEnteraBinaria + parteDecimalBinaria;
    
    return resultadoBinario;
    
    
  }
  
  // Método decimal a octal. Estático. Reutilizamos código
  
  public static String toOctal (String numero) {
    
    return Binario.toOctal(toBinario(numero));
    
  }
  
  // Método decimal a Hexadecimal. Estático. Reutilizamos código
  
  public static String toHexadecimal (String numero) {
    
    return Binario.toHexadecimal(toBinario(numero));
    
  }
  
  // -------------------------------------------- Operaciones ----------------------
  
  // Suma - Convertimos a double y sumamos, para volver a dar un String
  
  public String suma(Decimal decimal) {
    BigDecimal numeroBaseDecimal = new BigDecimal(numeroDecimal);
    BigDecimal numero2BaseDecimal = new BigDecimal(decimal.getNumeroDecimal());
    
    BigDecimal suma = numeroBaseDecimal.add(numero2BaseDecimal);
    return suma.toString();

  }
  
  // Al primero le resta el segundo
  public String resta(Decimal decimal) {
    BigDecimal numeroBaseDecimal = new BigDecimal(numeroDecimal);
    BigDecimal numero2BaseDecimal = new BigDecimal(decimal.getNumeroDecimal());
    
    BigDecimal resta = numeroBaseDecimal.subtract(numero2BaseDecimal);
    return resta.toString();

  }
  
  // Producto
  public String producto(Decimal decimal) {
    BigDecimal numeroBaseDecimal = new BigDecimal(numeroDecimal);
    BigDecimal numero2BaseDecimal = new BigDecimal(decimal.getNumeroDecimal());
    
    BigDecimal producto = numeroBaseDecimal.multiply(numero2BaseDecimal);
    return producto.toString();

  }
  
  // División. El primero lo divide entre el segundo
  public String division(Decimal decimal) {
    BigDecimal numeroBaseDecimal = new BigDecimal(numeroDecimal);
    BigDecimal numero2BaseDecimal = new BigDecimal(decimal.getNumeroDecimal());
    
    BigDecimal division = numeroBaseDecimal.divide(numero2BaseDecimal);
    return division.toString();

  } 
  
}
