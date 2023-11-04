package utilidades;

import java.util.Scanner;

import tiposDeNumeros.Binario;
import tiposDeNumeros.Decimal;
import tiposDeNumeros.Hexadecimal;
import tiposDeNumeros.Octal;

public class MetodosSimplificadores{
  
  private static  Scanner sc;
  
  public MetodosSimplificadores(Scanner sc) {
    this.sc = sc;
  }
  // ----------------------- Método para decir si es positivo o negativo, sacar parte entera y decimal. Estático. -----------------------
  
  public static String[] descompone (String numero) {
    
    // Devolvemos un array con 3 componentes. El primero será 1 o 0 dependiendo de si es o no negativo.
    // El segundo es la parte binaria entera y la tercera la parte binaria decimal. Si la tercera no existe, será null.
    
    String esNegativo = "0";
    
    if (numero.charAt(0) == '-') {
      
      numero = numero.substring(1);
      esNegativo = "1";
    }
    
    String binarioEntero;
    
    if(numero.indexOf('.') != -1)
      binarioEntero = numero.substring(0, numero.indexOf('.')).toUpperCase();
    else
      binarioEntero = numero.toUpperCase();
    
    String binarioDecimal = null;
    
    if (numero.indexOf('.') != -1) {
      
      binarioDecimal = numero.substring(numero.indexOf('.') + 1).toUpperCase();
    }
    
    String[] resultado = new String[3];
    resultado[0] = esNegativo;
    resultado[1] = binarioEntero;
    resultado[2] = binarioDecimal;
    
    return resultado;
  }
  
  // ----------------------- Método para solicitar el tipo de dato -----------------------

  public static String solicitaTipo() {
    String tipo;
    
    // Solicitamos el tipo de dato constantemente hasta obtener uno indicado.
    do {
      System.out.print("Indica el tipo de número a introducir ([D/d] Decimal, [O/o] Octal, [H/h] Hexadecimal, [B/b] Binario): ");
      tipo = sc.nextLine().toUpperCase();

      
      if (!tipo.equals("D") && !tipo.equals("O") && !tipo.equals("H") && !tipo.equals("B")) 
        System.out.println("El tipo de número no se encuentra entre los ofrecidos.");
      
    } while (!tipo.equals("D") && !tipo.equals("O") && !tipo.equals("H") && !tipo.equals("B"));
    
    return tipo;
  }
  
  // ----------------------- Método para solicitar el tipo de dato para finalizar el programa de operación -----------------------

  public static String solicitaTipoFinal() {
    String tipo;
    
    // Solicitamos el tipo de dato constantemente hasta obtener uno indicado.
    do {
      System.out.print("Indica el tipo de número que se va a devolver de la operación "
          + "([D/d] Decimal, [O/o] Octal, [H/h] Hexadecimal, [B/b] Binario): ");
      tipo = sc.nextLine().toUpperCase();

      
      if (!tipo.equals("D") && !tipo.equals("O") && !tipo.equals("H") && !tipo.equals("B")) 
        System.out.println("El tipo de número no se encuentra entre los ofrecidos.");
      
    } while (!tipo.equals("D") && !tipo.equals("O") && !tipo.equals("H") && !tipo.equals("B"));
    
    return tipo;
  }
  
  
  // ----------------------- Método estático para solicitar a la vez el tipo de número y el número -----------------------
  
  public static String[] solititaTipoYNumero() {

    String tipo;
    
    // Solicitamos el tipo de dato constantemente hasta obtener uno indicado.
    do {
      System.out.print("Indica el tipo de número a introducir ([D/d] Decimal, [O/o] Octal, [H/h] Hexadecimal, [B/b] Binario): ");
      tipo = sc.nextLine().toUpperCase();

      
      if (!tipo.equals("D") && !tipo.equals("O") && !tipo.equals("H") && !tipo.equals("B")) 
        System.out.println("El tipo de número no se encuentra entre los ofrecidos.");
      
    } while (!tipo.equals("D") && !tipo.equals("O") && !tipo.equals("H") && !tipo.equals("B"));
    
    
    // Preguntamos el número y vemos si corresponde con el tipo indicado. En caso de no serlo, volvemos a insistir. Distinguimos casos.
    boolean tipoDelNumeroAdecuado = false;
    String numeroIntroducido;
    
    do{
      System.out.print("Introduce, separando la parte decimal con un punto (.), un número ");
      
      switch(tipo) {
      
      case "D":

        System.out.print("decimal: ");
        numeroIntroducido = sc.nextLine();
        
        if(Decimal.isDecimal(numeroIntroducido)) tipoDelNumeroAdecuado = true;
        break;
        
      case "O":

        System.out.print("octal: ");
        numeroIntroducido = sc.nextLine();
        
        if(Octal.isOctal(numeroIntroducido)) tipoDelNumeroAdecuado = true;
        break;
        
      case "H":
        
        System.out.print("hexadecimal: ");
        numeroIntroducido = sc.nextLine();
        
        if(Hexadecimal.isHexadecimal(numeroIntroducido)) tipoDelNumeroAdecuado = true;
        break;

      default:
        
        System.out.print("binario: ");
        numeroIntroducido = sc.nextLine();
        
        if(Binario.isBinario(numeroIntroducido)) tipoDelNumeroAdecuado = true;
        break;
      }
      
      if(!tipoDelNumeroAdecuado) System.out.println("Debes introducir un número correcto y acorde al tipo indicado inicialmente.");
      
    }while(!tipoDelNumeroAdecuado);
    
    String[] array = new String[2];
    array[0] = tipo;
    array[1] = numeroIntroducido;
    
    return array;
    
  }
  
  
  
  // ----------------------- Método que calcule la conversión -----------------------
  
  public static void conversion(String tipoInicial, String tipoFinal, String numeroIntroducido) {
 // Resultado
    System.out.print("El resultado de la conversión es de: ");
    
    // binario a ->
    if (tipoInicial.equals("B") && tipoFinal.equals("D")) {
      System.out.println(Binario.binarioToDecimal(numeroIntroducido));
    } else if (tipoInicial.equals("B") && tipoFinal.equals("H")) {
      System.out.println(Binario.binarioToHexadecimal(numeroIntroducido));
    } else if (tipoInicial.equals("B") && tipoFinal.equals("O")) {
      System.out.println(Binario.binarioToOctal(numeroIntroducido));
    } else if (tipoInicial.equals("B") && tipoFinal.equals("B")) {
      System.out.println(Binario.binarioToBinario(numeroIntroducido));
      
    // decimal a ->  
    }else if (tipoInicial.equals("D") && tipoFinal.equals("D")) {
      System.out.println(Decimal.decimalToDecimal(numeroIntroducido));
    }else if (tipoInicial.equals("D") && tipoFinal.equals("H")) {
      System.out.println(Decimal.decimalToHexadecimal(numeroIntroducido));
    }else if (tipoInicial.equals("D") && tipoFinal.equals("O")) {
      System.out.println(Decimal.decimalToOctal(numeroIntroducido));
    }else if (tipoInicial.equals("D") && tipoFinal.equals("B")) {
      System.out.println(Decimal.decimalToBinario(numeroIntroducido));
    
    // Octal a ->
    }else if (tipoInicial.equals("O") && tipoFinal.equals("D")) {
      System.out.println(Octal.octalToDecimal(numeroIntroducido));
    }else if (tipoInicial.equals("O") && tipoFinal.equals("H")) {
      System.out.println(Octal.octalToHexadecimal(numeroIntroducido));
    }else if (tipoInicial.equals("O") && tipoFinal.equals("O")) {
      System.out.println(Octal.octalToOctal(numeroIntroducido));
    }else if (tipoInicial.equals("O") && tipoFinal.equals("B")) {
      System.out.println(Octal.octalToBinario(numeroIntroducido));
    
    // Hexadecimal a ->
    }else if (tipoInicial.equals("H") && tipoFinal.equals("D")) {
      System.out.println(Hexadecimal.hexadecimalToDecimal(numeroIntroducido));
    }else if (tipoInicial.equals("H") && tipoFinal.equals("H")) {
      System.out.println(Hexadecimal.hexadecimalToHexadecimal(numeroIntroducido));
    }else if (tipoInicial.equals("H") && tipoFinal.equals("O")) {
      System.out.println(Hexadecimal.hexadecimalToOctal(numeroIntroducido));
    }else if (tipoInicial.equals("H") && tipoFinal.equals("B")) {
      System.out.println(Hexadecimal.hexadecimalToBinario(numeroIntroducido));  
      
    }
  }
  
  // ----------------------- Método que solicite una operación -----------------------
  
  public static String solicitaOperacion() {

    String operacion;
    
    do {
      System.out.print("Introduce una operación ([S] Suma, [R] Resta, [P] Producto, [D] División): ");
      operacion = sc.nextLine().toUpperCase();
      
      if(!operacion.equals("S") && !operacion.equals("R") && !operacion.equals("P") && !operacion.equals("D"))
        System.out.println("Indica una operación válida por favor.");
      
    } while(!operacion.equals("S") && !operacion.equals("R") && !operacion.equals("P") && !operacion.equals("D"));
    
    return operacion;
  }
  
//----------------------- Método que realiza una operación -----------------------
  
  public static String realizaOperacion(String tipo1, String numero1, String tipo2, String numero2, String operacion, String tipoFinal) {
    
    // Para realizar la operación pasamos ambos números a decimales y operamos con ellos
    
    String numero1BaseDecimal;
    
    // Ya están los tipos en mayúsculas por como se han pedido
    if (tipo1.equals("D"))
      numero1BaseDecimal = Decimal.decimalToDecimal(numero1);
    else if (tipo1.equals("O"))
      numero1BaseDecimal = Octal.octalToDecimal(numero1);
    else if (tipo1.equals("H"))
      numero1BaseDecimal = Hexadecimal.hexadecimalToDecimal(numero1);
    else
      numero1BaseDecimal = Binario.binarioToDecimal(numero1);
    
    Decimal decimal1 = new Decimal(numero1BaseDecimal);
    
    // Pasamos el segundo número a decimal de la misma forma
    
    String numero2BaseDecimal;
    
    // Ya están los tipos en mayúsculas por como se han pedido
    if (tipo2.equals("D"))
      numero2BaseDecimal = Decimal.decimalToDecimal(numero2);
    else if (tipo2.equals("O"))
      numero2BaseDecimal = Octal.octalToDecimal(numero2);
    else if (tipo2.equals("H"))
      numero2BaseDecimal = Hexadecimal.hexadecimalToDecimal(numero2);
    else
      numero2BaseDecimal = Binario.binarioToDecimal(numero2);
    
    Decimal decimal2 = new Decimal(numero2BaseDecimal);
    
    //Realizamos la operación
    String resultadoOperacion;
    
    if (operacion.equals("S"))
      resultadoOperacion = decimal1.suma(decimal2);
    else if (operacion.equals("R"))
      resultadoOperacion = decimal1.resta(decimal2);
    else if (operacion.equals("P"))
      resultadoOperacion = decimal1.producto(decimal2);
    else
      resultadoOperacion = decimal1.division(decimal2);
    
    // Se realiza la conversión al tipo esperado
    String resultadoAImprimir;
    
    if (tipoFinal.equals("D"))
      resultadoAImprimir = Decimal.decimalToDecimal(resultadoOperacion);
    else if (tipoFinal.equals("H"))
      resultadoAImprimir = Decimal.decimalToHexadecimal(resultadoOperacion);
    else if (tipoFinal.equals("O"))
      resultadoAImprimir = Decimal.decimalToOctal(resultadoOperacion);
    else
      resultadoAImprimir = Decimal.decimalToBinario(resultadoOperacion);
    
    return resultadoAImprimir;
    
  }
  
}
