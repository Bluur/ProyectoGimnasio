
package Main;


import Clases.Empleado;
import Clases.Persona;
import Funciones.leerDatosTeclado;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GymTonificate {

    public static void main(String[] args) {
        System.out.println(menu());
    }
    
    private static int menu(){
        int eleccion;
        
        for(int i = 1; i < 9; i++){
            switch(i){
                case 1 -> System.out.println("1.- Alta personas");
                case 2 -> System.out.println("2.- Baja personas");
                case 3 -> System.out.println("3.- Modificar personas");
                case 4 -> System.out.println("4.- Listar personas existentes");
                case 5 -> System.out.println("5.- Listar personas por tipo");
                case 6 -> System.out.println("6.- Gestionar especialidades");
                case 7 -> System.out.println("7.- Gestionar trabajos");
                case 8 -> System.out.println("8.- Salir del programa");
            }
        }

        Calendar fechaNacimiento = new GregorianCalendar(1999, Calendar.AUGUST, 1999);
        Calendar calendario = new GregorianCalendar();

        Persona prueba = new Empleado("miguel", "77446461X", "direccion", "localidad", "provincia", "18006", "604101255", calendario, fechaNacimiento, 'H');

        eleccion = leerDatosTeclado.LeerEntero("¿Qué desea hacer?", 1, 8);


        return eleccion;
    }
}
