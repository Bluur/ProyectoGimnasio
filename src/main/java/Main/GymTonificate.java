
package Main;
import Clases.Empleado;
import Clases.Persona;
import Clases.Socio;
import Funciones.funcionesValidadoras;
import Funciones.leerDatosTeclado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GymTonificate {

    private static ArrayList <Persona> personas;
    private static ArrayList <String> listaEspecialidades;
    private static ArrayList <String> listaTrabajos;

    public static void main(String[] args) {
        //Inicializar estructuras de datos
        personas = new ArrayList<>();
        listaEspecialidades = new ArrayList<>();
        listaTrabajos = new ArrayList<>();

        //Mensaje de bienvenida
        System.out.println("Bienvenido a la Aplicación GymTonificate");
        System.out.println("A continuación le dejamos un menú con las opciones de gestión");

        //Recoger decisión del usuario
        int decision = menu();

        switch(decision){
            case 1 -> {

            }
        }
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

        eleccion = leerDatosTeclado.leerEntero("¿Qué desea hacer?", 1, 8);

        return eleccion;
    }

    public static void altaPersonas(Persona nueva, String DNI){

    }

    public static Persona crearPersona(int tipo){
        //Variables
        String nombre = leerDatosTeclado.leerString("Dame tu nombre");

        String DNI;
        do{
           DNI = leerDatosTeclado.leerString("Dame tu DNI");
        }while(!funcionesValidadoras.validarId(DNI));

        String direccion = leerDatosTeclado.leerString("Dame tu dirección");
        String localidad = leerDatosTeclado.leerString("Dame tu localidad");
        String provincia = leerDatosTeclado.leerString("Dame tu provincia");

        String codigoPostal;
        do {
            codigoPostal = leerDatosTeclado.leerString("Dame tu codigoPostal");
        }while(codigoPostal.length() != 5);

        String telefono;
        do {
            telefono = leerDatosTeclado.leerString("Dame tu teléfono");
        }while(telefono.length() != 9);

        Calendar fechaAlta = new GregorianCalendar();

        int dia = leerDatosTeclado.leerEntero("Dame tu día de nacimiento", 1, 31);
        int mes = leerDatosTeclado.leerEntero("Dame tu mes de nacimiento", 1, 12);
        int year;
        do{
            year = leerDatosTeclado.leerEntero("Dame tu edad");
        }while(fechaAlta.get(Calendar.YEAR) - year > 99);

        Calendar fechaNacimiento = new GregorianCalendar(year, mes-1, dia);

        char sexo = leerDatosTeclado.leerChar("Dame tu sexo H/M", 'M', 'M');

        Persona nueva;
        //Socio, monitor y trabajador
        if(tipo == 1){
            int sesiones = leerDatosTeclado.leerEntero("Dame tu numero de sesiones", 2, 6);
            boolean pagado = leerDatosTeclado.leerEntero("Introduzca 1 si está pagado o 0 sino", 0, 1) == 0;
            String lesiones = leerDatosTeclado.leerString("Introduzca las lesiones que tenga");
            nueva = new Socio(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo, sesiones, pagado, lesiones);
        } else if(tipo == 2){

        }else{

        }

        return nueva;
    }
}
