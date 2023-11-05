package application;

import java.util.Scanner;

import utilidades.MetodosSimplificadores;

public class Calculadora {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    
    MetodosSimplificadores ms = new MetodosSimplificadores();
    
    ms.setSc(sc);
    
    String flag;
    
    do {
      
      do {
        
        
        // Damos 3 opciones: Operación, Conversión o salida del programa. Solicitamos insistentemente una de las 3.
        
        System.out.print("Introduce [O] para realizar una operación básica, [C] para realizar una conversión de tipos o [S] para salir: ");
        flag = sc.nextLine().toUpperCase();
        
        if(!flag.equals("O") && !flag.equals("C") && !flag.equals("S"))
          System.out.println("La operación indicada no es válida, prueba de nuevo.");
        

      } while (!flag.equals("O") && !flag.equals("C") && !flag.equals("S"));
      
      // Opción de la Conversión
      if (flag.equals("C")) {
        
        String[] tipoYNumero = MetodosSimplificadores.solititaTipoYNumero();
        
        String tipo = tipoYNumero[0];
        String numeroIntroducido = tipoYNumero[1];
            
        
        // Solicitamos el tipo de dato al que vamos a realizar la conversión constantemente hasta obtener uno indicado entre las opciones.
        String tipoFinal = MetodosSimplificadores.solicitaTipo();
        
        
        // Imprimimos por pantalla la conversión
        
        MetodosSimplificadores.conversion(tipo, tipoFinal, numeroIntroducido);
        
      } else if (flag.equals("O")) {
        
        // Solicitamos los 2 números con sus respectivos tipos
        
        String[] tipoYNumero1 = MetodosSimplificadores.solititaTipoYNumero();
        
        String tipo1 = tipoYNumero1[0];
        String numeroIntroducido1 = tipoYNumero1[1];
        
        String[] tipoYNumero2 = MetodosSimplificadores.solititaTipoYNumero();
        
        String tipo2 = tipoYNumero2[0];
        String numeroIntroducido2 = tipoYNumero2[1];
        
        // Preguntamos por la operación a realizar
        
        String operacion = MetodosSimplificadores.solicitaOperacion();
        
        String tipoFinal = MetodosSimplificadores.solicitaTipoFinal();
        
        System.out.println(MetodosSimplificadores.realizaOperacion
            (tipo1, numeroIntroducido1, tipo2, numeroIntroducido2, operacion, tipoFinal));

      }
      
      System.out.println();
      
    } while (!flag.equals("S"));

    
  }

}
