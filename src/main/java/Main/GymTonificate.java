
package Main;
import Clases.Empleado;
import Clases.Monitor;
import Clases.Persona;
import Clases.Socio;
import Funciones.funcionesValidadoras;
import Funciones.leerDatosTeclado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class GymTonificate {

    private static ArrayList <Persona> personas;
    private static ArrayList <String> listaEspecialidades;
    private static ArrayList <String> listaTrabajos;

    public static void main(String[] args) {
        final int ALTA = 1;
        final int BAJA = 2;
        final int MODIFICAR = 3;
        final int LISTARPERSONAS = 4;
        final int LISTARTIPO = 5;
        final int GESTIONARESPECS = 6;
        final int GESTIONARTRABAJOS = 7;
        final int SALIR = 8;

        //Inicializar estructuras de datos
        personas = new ArrayList<>();
        listaEspecialidades = new ArrayList<>();
        listaTrabajos = new ArrayList<>();

        //Mensaje de bienvenida
        System.out.println("Bienvenido a la Aplicación GymTonificate");
        System.out.println("A continuación le dejamos un menú con las opciones de gestión");

        int decision;
        //Bucle principal
        do {
            //Recoger decisión del usuario
            decision = menu();

            switch (decision) {
                case ALTA -> {
                    Persona nueva;
                    do {
                        int tipo = tipoPersona();
                        nueva = altaPersona(tipo);
                    } while (nueva == null);
                    personas.add(nueva);
                }
                case BAJA -> {
                    String documento = leerDatosTeclado.leerString("Deme su ID para borrar su cuenta (NIF,NIE,CIF)");
                    personas.removeIf(s -> s.getDNI().equals(documento));
                }
                case MODIFICAR -> {
                    String documento = leerDatosTeclado.leerString("Deme su ID para modificar su cuenta (NIF,NIE,CIF)");
                    int posicion = 0;
                    boolean modificar = false;
                    if(!personas.isEmpty()) {
                        if (funcionesValidadoras.validarId(documento)) {
                            for (Persona c : personas) {
                                if (c != null && c.getDNI().equals(documento)) {
                                    posicion = personas.indexOf(c);
                                    modificar = true;
                                }
                            }
                            if (modificar) {
                                modificarPersona(posicion);
                            }
                        } else {
                            System.out.println("Su DNI no es válido, inténtelo de nuevo");
                        }
                    }else{
                        System.out.println("No hay personas registradas ahora mismo");
                    }
                }
            }
        } while (decision != SALIR);
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

    /**
     * Lee el tipo de persona que se intenta registrar y lo valida
     * @return int (Socio => 1), (Monitor => 2) y (Empleado => 3)
     */
    private static int tipoPersona(){
        int decision;
        do{
            decision = leerDatosTeclado.leerEntero("Tipo persona -> (Socio => 1) (Monitor => 2) (Empleado => 3)", 1, 3);
        }while(decision != 1 && decision != 2 && decision != 3);
        return decision;
    }

    /**
     * Función que recibe el tipo de Persona a crear, se encarga de pedir los datos del usuario y redirigirlos dependiendo
     * de que tipo de usuario se quiera crear.
     *
     * @param tipo 1 => Socio | 2 => Monitor | 3 => Empleado
     * @return <em>Persona</em>
     */
    public static Persona altaPersona(final int tipo){
        final int SOCIO = 1;
        final int MONITOR = 2;
        final int EMPLEADO = 3;

        if(tipo != SOCIO && tipo != MONITOR && tipo != EMPLEADO){
            throw new IllegalArgumentException("El tipo no es válido");
        }

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

        Calendar fechaNacimiento = leerFechaNacimiento(fechaAlta);

        char sexo = leerDatosTeclado.leerChar("Dame tu sexo H/M", 'H', 'M');

        Persona nueva;

        switch(tipo) {
            case SOCIO -> {
                //Sesiones
                int sesiones = leerDatosTeclado.leerEntero("Dame tu numero de sesiones", 2, 6);
                //Pagado
                boolean pagado = leerDatosTeclado.leerEntero("Introduzca 1 si está pagado o 0 sino", 0, 1) == 0;
                //Lesiones
                String lesiones = leerDatosTeclado.leerString("Introduzca las lesiones que tenga");
                nueva = new Socio(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo, sesiones, pagado, lesiones);
            }
            case MONITOR -> {
                boolean continuar = false;
                String especialidad;
                boolean activo;

                //Comprobar la especialidad
                do{
                    //Especialidades
                    especialidad = leerDatosTeclado.leerString("Deme su especialidad");
                }while(!contains(especialidad, listaEspecialidades));

                //Sueldo
                float sueldo = leerDatosTeclado.leerFloat("Deme el sueldo", 950);

                //Activo
                char decision = leerDatosTeclado.leerChar("Está activo (S/N => (Default = S))");
                activo = decision != 'N';

                nueva = new Monitor(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo, especialidad, sueldo, activo);
            }
            case EMPLEADO -> {
                String tipoTrabajo;
                float sueldo;
                String extension;

                //Tipo de trabajo
                do{
                    tipoTrabajo = leerDatosTeclado.leerString("Deme su tipo de trabajo");
                }while(!contains(tipoTrabajo, listaTrabajos));

                //Sueldo
                sueldo = leerDatosTeclado.leerFloat("Deme su sueldo",950);

                //extension
                extension = leerDatosTeclado.leerString("Indique la extensión telefónica");

                nueva = new Empleado(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo, tipoTrabajo, sueldo, extension);
            }
            default -> {
                System.out.println("Los datos introducidos no son válidos");
                nueva = null;
            }
        }
        return nueva;
    }

    /**
     * Modifica los cambios de la persona
     * @param posicion Posición de la persona a modificar
     */
    public static void modificarPersona(final int posicion){
        System.out.println("¿Que campo desea modificar?");

        //Nombre
        System.out.println("Nombre => " + personas.get(posicion).getNombre());
        String eleccion = leerDatosTeclado.leerString("Introduzca un nombre si desea cambiarlo, sino pulse enter: ");
        if(!eleccion.isEmpty()){
            personas.get(posicion).setNombre(eleccion);
        }

        //DNI
        System.out.println("DNI => "+personas.get(posicion).getDNI());
        eleccion = leerDatosTeclado.leerString("Introduzca un DNI si desea cambiarlo, sino, pulse enter");
        while(!eleccion.isEmpty()) {
            if(funcionesValidadoras.validarId(eleccion)){
                personas.get(posicion).setDNI(eleccion);
            }else{
                eleccion = leerDatosTeclado.leerString("Introduzcalo de nuevo o pulse enter");
            }
        }

        //Dirección
        System.out.println("Dirección => "+personas.get(posicion).getDireccion());
        eleccion = leerDatosTeclado.leerString("Introduzca una dirección si desea cambiarla, sino, pulse enter");
        if(!eleccion.isEmpty()){
            personas.get(posicion).setDireccion(eleccion);
        }

        //Localidad
        System.out.println("Localidad => "+personas.get(posicion).getLocalidad());
        eleccion = leerDatosTeclado.leerString("Introduzca una localidad si desea cambiarla, sino, pulse enter");
        if(!eleccion.isEmpty()){
            personas.get(posicion).setLocalidad(eleccion);
        }

        //Provincia
        System.out.println("Provincia => "+personas.get(posicion).getProvincia());
        eleccion = leerDatosTeclado.leerString("Introduzca una provincia si desea cambiarla, sino, pulse enter");
        if(!eleccion.isEmpty()){
            personas.get(posicion).setProvincia(eleccion);
        }

        //Codigo Postal
        System.out.println("Código postal => "+personas.get(posicion).getCodigoPostal());
        eleccion = leerDatosTeclado.leerString("Introduzca un código postal si desea cambiarlo, sino, pulse enter");
        while(!eleccion.isEmpty()){
            if(eleccion.length() == 5) {
                personas.get(posicion).setCodigoPostal(eleccion);
            }else{
                eleccion = leerDatosTeclado.leerString("Introduzcalo de nuevo o pulse enter");
            }
        }

        //Teléfono
        System.out.println("Teléfono => "+personas.get(posicion).getTelefono());
        eleccion = leerDatosTeclado.leerString("Introduzca un teléfono si desea cambiarlo, sino, pulse enter");
        while(!eleccion.isEmpty()){
            if(eleccion.length() == 9) {
                personas.get(posicion).setTelefono(eleccion);
            }else{
                eleccion = leerDatosTeclado.leerString("Introduzcalo de nuevo o pulse enter");
            }
        }

        //Fecha Nacimiento
        System.out.println("Fecha nacimiento => "+personas.get(posicion).getDireccion());
        eleccion = leerDatosTeclado.leerString("¿Quiere cambiarla? (S/N)").toUpperCase(Locale.ROOT);
        if(eleccion.charAt(0) == 'S'){
            int dia = leerDatosTeclado.leerEntero("Introduzca el día de su nacimiento", 1, 31);
            int mes = leerDatosTeclado.leerEntero("Introduzca el mes de su nacimiento", 1 , 12);
            mes += 1;
            int year;
            do{
                year = leerDatosTeclado.leerEntero("Introduzca el año de su nacimiento");
            }while(Calendar.YEAR - year > 99);
            Calendar nuevo = new GregorianCalendar(year, mes, dia);
            personas.get(posicion).setFechaNacimiento(nuevo);
        }

        //Sexo
        System.out.println("Sexo => "+personas.get(posicion).getSexo());
        eleccion = leerDatosTeclado.leerString("Introduzca H/M si desea cambiarlo, sino, pulse enter");
        if(!eleccion.isEmpty() && eleccion.charAt(0) == 'H'){
            personas.get(posicion).setSexo('H');
        }else if(!eleccion.isEmpty() && eleccion.charAt(0) == 'M'){
            personas.get(posicion).setSexo('M');
        }

        //Atributos concretos de cada subclase
        if(personas.get(posicion) instanceof Socio){
            //Sesiones
            System.out.println("Sesiones =>"+ ((Socio) personas.get(posicion)).getSesionesSemanales());
            eleccion = leerDatosTeclado.leerString("Pulse enter para continuar o cualquier caracter para cambiarlas");
            if(!eleccion.isEmpty()){
                int sesiones = leerDatosTeclado.leerEntero("Introduzca las sesiones que desea: ", 2, 6);
                ((Socio) personas.get(posicion)).setSesionesSemanales(sesiones);
            }
            //Pagado
            if(!((Socio) personas.get(posicion)).isPagado()){
                eleccion = leerDatosTeclado.leerString("¿No ha pagado sus cuotas, desea pagarlas? (S/N)");
                if(!eleccion.isEmpty() && eleccion.charAt(0) == 'S'){
                    ((Socio) personas.get(posicion)).setPagado(true);
                }
            }
            //Lesiones
            eleccion = leerDatosTeclado.leerString("Introduzca nuevas lesiones si las tiene, sino, pulse enter");
            if(!eleccion.isEmpty()){
                ((Socio) personas.get(posicion)).setLesiones(eleccion);
            }
        }else if(personas.get(posicion) instanceof Monitor){
            //Sueldo
            System.out.println("Sueldo => "+((Socio) personas.get(posicion)).getCuota());
            eleccion = leerDatosTeclado.leerString("Desea cambiar el sueldo de "+personas.get(posicion).getNombre()+"?" +
                    "Pulse enter para continuar sin cambios, cualquier letra para cambiarlo");
            if(!eleccion.isEmpty()){
                float sueldo = leerDatosTeclado.leerFloat("Introduzca el sueldo nuevo", 950);
                ((Monitor) personas.get(posicion)).setSueldo(sueldo);
            }
            //Especialidad
            System.out.println("Especialidad => "+((Monitor) personas.get(posicion)).getEspecialidad());
            eleccion = leerDatosTeclado.leerString("Si desea cambiar su especialidad, introduzcala, sino, pulse enter");
            boolean cambiar = false;
            if(!eleccion.isEmpty()){
                for(String esp:listaEspecialidades){
                    if(eleccion.equals(esp)){
                        cambiar = true;
                    }
                }
                if(cambiar){
                    ((Monitor) personas.get(posicion)).setEspecialidad(eleccion);
                }
            }
            //Activo
            System.out.println("Activo => " +((Monitor) personas.get(posicion)).isActivo());
            eleccion = leerDatosTeclado.leerString("¿Quiere cambiar su estado actual?" +
                    "Pulse enter para continuar o introduzca cualquier carácter para cambiarlo");
            if(!eleccion.isEmpty()){
                ((Monitor) personas.get(posicion)).setActivo(!((Monitor) personas.get(posicion)).isActivo());
            }
        }else{

        }
        System.out.println(personas.get(posicion));
    }

    /**
     * Función que crea un Calendar según los datos del Usuario
     * @param fechaAlta fecha en la que se dió de alta con la que se validará la edad
     * @return Calendar con la fecha de nacimiento del usuario
     */
    public static Calendar leerFechaNacimiento(Calendar fechaAlta){
        int alta = fechaAlta.get(Calendar.YEAR);
        int dia = leerDatosTeclado.leerEntero("Dame tu día de nacimiento", 1, 31);
        int mes = (leerDatosTeclado.leerEntero("Dame tu mes de nacimiento", 1, 12))+1;
        int year;
        do{
            year = leerDatosTeclado.leerEntero("Dame tu año de nacimiento");
        }while(alta - year > 99);

        Calendar nacimiento = new GregorianCalendar(year, mes, dia);
        return nacimiento;
    }

    /**
     * Función que recibe un String y que busca en un array que le pasamos, devuelve
     * True si el array contiene el String
     * False si el array no lo contiene
     * @param aBuscar String a buscar
     * @param lista ArrayList en el que buscar
     * @return boolean
     */
    public static boolean contains(String aBuscar, ArrayList<String> lista){
        boolean contiene;
        if(!lista.contains(aBuscar)){
            contiene = true;
        }else{
            contiene = false;
            System.out.println("Ese no está registrado, inténtelo de nuevo");
        }
        return contiene;
    }
}
