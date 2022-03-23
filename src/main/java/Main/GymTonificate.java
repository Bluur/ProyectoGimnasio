package Main;

import Clases.Empleado;
import Clases.Monitor;
import Clases.Persona;
import Clases.Socio;

//Para validar el DNI/NIE/NIF
import Funciones.FuncionesValidadoras;
//Para validar el input del usuario
import Funciones.LeerDatosTeclado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase que contiene el MAIN y las funciones que este necesita
 * MAIN => Programa main
 * CARGARESPECIALIDADES => Se encarga de cargar al principio las especialidades
 * CARGARTRABAJOS => Se encarga de cargar al principìo los trabajos
 * CARGARPERSONAS => Se encarga de cargar al principio las personas
 * MENU => Menú principal que recoge la elección del usuario
 * TIPOPERSONA => Lee el tipo de persona validando el input del usuario
 * ALTAPERSONA => Se encarga de registrar a una persona pidiendo todos los datos y validándolos
 * MODIFICARPERSONA => Big ol chunk de código spaghetti que modifica la persona
 * LEERFECHANACIMIENTO => Crea un calendar con el input del usuario
 * CONTAINS => Busca en el array de Strings ya sea trabajo o especialidades
 * CONTINUAR => Función que confirma una adición o eliminación del algún aspecto del programa
 * ELIMINARESPECIALIDADES => Función que elimina especialidades
 * ELIMINARTRABAJOS => Función que elimina trabajos
 * @author Migue
 */
public class GymTonificate {

    private static ArrayList<Persona> personas;
    private static ArrayList<String> listaEspecialidades;
    private static ArrayList<String> listaTrabajos;
    private static final String DATOSPERSONAS =  "datosPersonas.dat";

    public static void main(String[] args) {
        final int ALTA = 1;
        final int BAJA = 2;
        final int MODIFICAR = 3;
        final int LISTARPERSONAS = 4;
        final int LISTARTIPO = 5;
        final int GESTIONARESPECS = 6;
        final int GESTIONARTRABAJOS = 7;
        final int SALIR = 8;

        //Ejecuta la función que carga datos de un archivo
        personas = Funciones.funcionesLecturaEscritura.leerDatos(DATOSPERSONAS);

        //En caso de no haber datos crea un array vacio y carga unos datos por defecto
        if(personas == null){
            personas = new ArrayList<>();
            System.out.println("No se han podido cargar los datos");
            cargarPersonas(personas);
        }else{
            System.out.println("Personas Cargadas");
        }

        listaEspecialidades = new ArrayList<>();
        listaTrabajos = new ArrayList<>();

        cargarEspecialidades(listaEspecialidades);
        cargarTrabajos(listaTrabajos);

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

                    //Si contiene ya una persona con ese DNI no la añade
                    if (!personas.contains(nueva)) {
                        personas.add(nueva);
                    } else {
                        System.out.println("No se ha podido añadir porque ya esta presente en nuestra base de datos");
                    }
                }
                case BAJA -> {
                    String documento = LeerDatosTeclado.leerString("Deme su ID para borrar su cuenta (NIF,NIE,CIF)");
                    for(Persona c:personas){
                        if(c != null && c.getDNI().equals(documento)){
                            personas.remove(c);
                        }else{
                            System.out.println("Su DNI no está presente en la base de datos");
                        }
                    }
                    
                    if(personas.isEmpty()){
                        System.out.println("No constamos con usuarios en este momento inténtelo más tarde");
                    }
                }
                case MODIFICAR -> {
                    String documento = LeerDatosTeclado.leerString("Deme su ID para modificar su cuenta (NIF,NIE,CIF)");
                    int posicion = 0;
                    boolean modificar = false;

                    //Verifica si esta vacio el array personas
                    if (!personas.isEmpty()) {
                        //Verifica validez del documento
                        if (FuncionesValidadoras.validarId(documento)) {
                            //Busca el documento
                            for (Persona c : personas) {
                                //Comprueba que no sea nulo para no tener un pointer exception y compara
                                if (c != null && c.getDNI().equals(documento)) {
                                    //Guarda posición y cambia modificar a true
                                    posicion = personas.indexOf(c);
                                    modificar = true;
                                }
                            }
                            //Si modificar está activado inicializa la función modificarPersona()
                            if (modificar) {
                                modificarPersona(posicion);
                                System.out.println("Sus Nuevos datos son: " + personas.get(posicion).toString());
                            }
                            //Si el documento no esta lanza un mensaje
                        } else {
                            System.out.println("Su DNI no es válido, inténtelo de nuevo");
                        }
                    } else {
                        //Si está vacío
                        System.out.println("No hay personas registradas ahora mismo");
                    }
                }
                case LISTARPERSONAS -> {
                    for (Persona c : personas) {
                        System.out.println(c.toString());
                    }
                }
                case LISTARTIPO -> {
                    final int SOCIO = 1;
                    final int MONITOR = 2;
                    final int EMPLEADO = 3;

                    int tipo = tipoPersona();

                    switch (tipo) {
                        case SOCIO -> {
                            for (Persona c : personas) {
                                if (c instanceof Socio) {
                                    System.out.println(c.toString());
                                }
                            }
                        }
                        case MONITOR -> {
                            for (Persona c : personas) {
                                if (c instanceof Monitor) {
                                    System.out.println(c.toString());
                                }
                            }
                        }
                        case EMPLEADO -> {
                            for (Persona c : personas) {
                                if (c instanceof Empleado) {
                                    System.out.println(c.toString());
                                }
                            }
                        }
                    }
                }
                case GESTIONARESPECS -> {
                    int gestion;
                    final int LIST = 1;
                    final int ADD = 2;
                    final int REMOVE = 3;
                    final int CONTINUAR = 4;


                    int contador = 1;

                    do {
                        gestion = LeerDatosTeclado.leerEntero("Gestión especialidades -> (Listar => 1) (Añadir => 2) (Eliminar => 3) (Continuar => 4)", 1, 4);
                        switch (gestion) {
                            case LIST -> {
                                for (String s : listaEspecialidades) {
                                    String numero = "" + contador + ".-";
                                    System.out.println(numero + s);
                                    contador++;
                                }
                            }
                            case ADD -> {
                                String add = LeerDatosTeclado.leerString("Introduce la nueva especialidad");
                                boolean continuar = continuar(add, 1);
                                if (continuar) {
                                    listaEspecialidades.add(add);
                                }
                            }
                            case REMOVE -> {
                                eliminarEspecialidades(listaEspecialidades);
                            }
                        }
                    }while(gestion != CONTINUAR);
                }
                case GESTIONARTRABAJOS -> {
                    int gestion;
                    final int LIST = 1;
                    final int ADD = 2;
                    final int REMOVE = 3;
                    final int CONTINUAR = 4;

                    int contador = 1;

                    do {
                        gestion = LeerDatosTeclado.leerEntero("Gestión especialidades -> (Listar => 1) (Añadir => 2) (Eliminar => 3) (Continuar => 4)", 1, 4);
                        switch (gestion) {
                            case LIST -> {
                                for (String s : listaTrabajos) {
                                    String numero = "" + contador + ".-";
                                    System.out.println(numero + s);
                                    contador++;
                                }
                            }
                            case ADD -> {
                                String add = LeerDatosTeclado.leerString("Introduce el nuevo trabajo");
                                //Continuar(String add, int accion) => accion = 1 añadir -> 1 borrar -> 2
                                boolean continuar = continuar(add, 1);
                                if (continuar) {
                                    listaTrabajos.add(add);
                                }
                            }
                            case REMOVE -> {
                                eliminarTrabajos(listaTrabajos, personas);
                            }
                        }
                    }while(gestion != CONTINUAR);
                }

            }
        } while (decision != SALIR);
        Funciones.funcionesLecturaEscritura.guardarDatos(personas, DATOSPERSONAS);
    }

    /**
     * Carga especialidades en el programa
     * @param listaEspecialidades Referencia al array en el que cargarlos
     */
    private static void cargarEspecialidades(ArrayList<String> listaEspecialidades) {
        listaEspecialidades.add("Aerobic");
        listaEspecialidades.add("Calistenia");
        listaEspecialidades.add("Crossfit");
    }

    /**
     * Carga trabajos para comenzar el programa
     * @param listaTrabajos Referencia al array en el que cargarlos
     */
    private static void cargarTrabajos(ArrayList<String> listaTrabajos) {
        listaTrabajos.add("Limpiador");
        listaTrabajos.add("Asistente");
        listaTrabajos.add("Organizador");
    }

    /**
     * Carga personas para comenzar el programa
     * @param personas Referencia al array en el que cargarlos
     */
    private static void cargarPersonas(ArrayList<Persona> personas) {
        Calendar fechaAlta = new GregorianCalendar();
        Calendar fechaNac1 = new GregorianCalendar(1999, Calendar.AUGUST, 17);
        Calendar fechaNac2 = new GregorianCalendar(2003, Calendar.AUGUST, 5);
        Calendar fechaNac3 = new GregorianCalendar(1980, Calendar.AUGUST, 25);
        Calendar fechaNac4 = new GregorianCalendar(1961, Calendar.JANUARY, 11);

        String[] especialidades;


        Persona socio1 = new Socio("Miguel", "77446461X", "Almuñecar 10", "Granada", "Granada", "18006", "604101255", fechaAlta, fechaNac1, 'H', 2, true, "Dierna");
        Persona socio2 = new Socio("Pepe", "S5690421B", "Avenida América 5","Granada", "Granada", "18006", "958088882", fechaAlta, fechaNac2, 'H', 4, false, "");
        Persona socio3 = new Socio("Yolanda", "42616015M", "Almendros 2", "Alhendín", "Granada", "18200", "680325775", fechaAlta, fechaNac3, 'M', 3, true, "Artrosis");

        especialidades = new String[]{"Crossfit"};
        Persona monitor1 = new Monitor("Roberto", "Z4425015G", "Doctor marañón", "Armilla", "Granada", "18100", "611352928", fechaAlta, fechaNac1, 'H', especialidades, 950, true);
        especialidades = new String[]{"Calistenia"};
        Persona monitor2 = new Monitor("Eva", "05939959W", "Habana 3", "Churriana de la Vega", "Granada", "18799", "858292835", fechaAlta, fechaNac2, 'M', especialidades, 980, false);
        especialidades = new String[]{"Aerobic", "Calistenia"};
        Persona monitor3 = new Monitor("Ana María", "Z4425015G", "Tuerce Espina 3", "Tuerce Espina", "Reinos del Este", "11111", "918238989", fechaAlta, fechaNac3, 'M', especialidades, 1500, true);

        Persona empleado1 = new Empleado("Alberto", "81699080K", "Alhama 3", "Alhama de Granada", "Granada", "18002", "958945139", fechaAlta, fechaNac1, 'H', "Limpiador", 1200, "+23");
        Persona empleado2 = new Empleado("María", "Y3753738Z", "Orgrimmar 3", "Orgrimmar", "Durotar", "66666", "626364657", fechaAlta, fechaNac3, 'M', "Organizador", 1600, "+66");
        Persona empleado3 = new Empleado("Diana", "U57812752", "Casa de la Dios 1", "Temiscira", "Mar mediterránero", "11111", "989324565", fechaAlta, fechaNac4, 'M', "Asistente", 2000, "+11");

        personas.add(socio1);
        personas.add(socio2);
        personas.add(socio3);
        personas.add(monitor1);
        personas.add(monitor2);
        personas.add(monitor3);
        personas.add(empleado1);
        personas.add(empleado2);
        personas.add(empleado3);
    }

    /**
     * Menú principal con el que sacaremos las opciones por pantalla y
     * recogeremos los datos
     *
     * @return int con la elección del usuario
     */
    private static int menu() {
        int eleccion;

        for (int i = 1; i < 9; i++) {
            switch (i) {
                case 1 ->
                    System.out.println("1.- Alta personas");
                case 2 ->
                    System.out.println("2.- Baja personas");
                case 3 ->
                    System.out.println("3.- Modificar personas");
                case 4 ->
                    System.out.println("4.- Listar personas existentes");
                case 5 ->
                    System.out.println("5.- Listar personas por tipo");
                case 6 ->
                    System.out.println("6.- Gestionar especialidades");
                case 7 ->
                    System.out.println("7.- Gestionar trabajos");
                case 8 ->
                    System.out.println("8.- Salir del programa");
            }
        }

        eleccion = LeerDatosTeclado.leerEntero("¿Qué desea hacer?", 1, 8);

        return eleccion;
    }

    /**
     * Lee el tipo de persona que se intenta registrar y lo valida
     *
     * @return int (Socio => 1), (Monitor => 2) y (Empleado => 3)
     */
    private static int tipoPersona() {
        int decision;
        do {
            decision = LeerDatosTeclado.leerEntero("Tipo persona -> (Socio => 1) (Monitor => 2) (Empleado => 3)", 1, 3);
        } while (decision != 1 && decision != 2 && decision != 3);
        return decision;
    }

    /**
     * Función que recibe el tipo de Persona a crear, se encarga de pedir los
     * datos del usuario y redirigirlos dependiendo de que tipo de usuario se
     * quiera crear.
     *
     * @param tipo 1 => Socio | 2 => Monitor | 3 => Empleado
     * @return <em>Persona</em>
     */
    public static Persona altaPersona(final int tipo) {
        final int SOCIO = 1;
        final int MONITOR = 2;
        final int EMPLEADO = 3;

        if (tipo != SOCIO && tipo != MONITOR && tipo != EMPLEADO) {
            throw new IllegalArgumentException("El tipo no es válido");
        }

        //Variables
        String nombre = LeerDatosTeclado.leerString("Dame tu nombre");

        String DNI;
        do {
            DNI = LeerDatosTeclado.leerString("Dame tu DNI");
        } while (!FuncionesValidadoras.validarId(DNI));

        String direccion = LeerDatosTeclado.leerString("Dame tu dirección");
        String localidad = LeerDatosTeclado.leerString("Dame tu localidad");
        String provincia = LeerDatosTeclado.leerString("Dame tu provincia");

        String codigoPostal;
        do {
            codigoPostal = LeerDatosTeclado.leerString("Dame tu codigoPostal");
        } while (codigoPostal.length() != 5);

        String telefono;
        do {
            telefono = LeerDatosTeclado.leerString("Dame tu teléfono");
        } while (telefono.length() != 9);

        Calendar fechaAlta = new GregorianCalendar();
        Calendar fechaNacimiento = leerFechaNacimiento(fechaAlta);
        char sexo = LeerDatosTeclado.leerChar("Dame tu sexo H/M", 'H', 'M');
        Persona nueva;

        switch (tipo) {
            case SOCIO -> {
                //Sesiones
                int sesiones = LeerDatosTeclado.leerEntero("Dame tu numero de sesiones", 2, 6);
                //Pagado
                boolean pagado = LeerDatosTeclado.leerEntero("Introduzca 1 si está pagado o 0 sino", 0, 1) == 0;
                //Lesiones
                String lesiones = LeerDatosTeclado.leerString("Introduzca las lesiones que tenga");
                nueva = new Socio(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo, sesiones, pagado, lesiones);
            }
            case MONITOR -> {
                ArrayList<String> especialidades = new ArrayList<>();

                System.out.println("Esta es la lista de especialidades de la que disponemos: " + listaEspecialidades);
                String especialidad;
                
                //Comprobar la especialidad
                do {
                    especialidad = LeerDatosTeclado.leerString("Deme sus especialidades, introduzca enter para parar (Max 3)");

                    //Si la lista contiene la especialidad introducida recorre el array
                    if (listaEspecialidades.contains(especialidad) && !especialidades.contains(especialidad)) {
                        especialidades.add(especialidad);
                    } else if (listaEspecialidades.contains(especialidad)) {
                        System.out.println("No tenemos esa especialidad, inténtelo de nuevo");
                    } else {
                        System.out.println("Ya tienes esa especialidad");
                    }
                    //Mientras el input no esté vacio y el índice 3 del array sea nulo
                } while (!especialidad.isEmpty() && especialidades.size() <= 3);

                String[] array = new String[3];
                array = especialidades.toArray(array);
                for (String s : array) {
                    System.out.println(s);
                }

                //Sueldo
                float sueldo = LeerDatosTeclado.leerFloat("Deme el sueldo", 950);

                //Activo
                boolean activo;
                char decision = LeerDatosTeclado.leerChar("¿Está activo? (S/N)");
                activo = decision != 'N';

                nueva = new Monitor(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo, array, sueldo, activo);
            }
            case EMPLEADO -> {
                String tipoTrabajo;
                float sueldo;
                String extension;

                //Tipo de trabajo
                do {
                    System.out.println("Estos son los trabajos disponibles : \n" + listaTrabajos.toString());
                    tipoTrabajo = LeerDatosTeclado.leerString("Deme su tipo de trabajo");
                } while (!contains(tipoTrabajo, listaTrabajos));

                //Sueldo
                sueldo = LeerDatosTeclado.leerFloat("Deme su sueldo", 950);

                //extension
                extension = LeerDatosTeclado.leerString("Indique la extensión telefónica");

                nueva = new Empleado(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo, tipoTrabajo, sueldo, extension);
            }
            default -> {
                throw new IllegalArgumentException("No se ha introducido el tipo de persona apropiadamente");
            }
        }
        return nueva;
    }

    /**
     * Modifica los cambios de la persona
     *
     * @param posicion Posición de la persona a modificar
     */
    public static void modificarPersona(final int posicion) {
        System.out.println("¿Que campo desea modificar?");
        boolean continuar = false;

        Persona aEditar = personas.get(posicion);
        
        //Nombre
        System.out.println("Nombre => " + aEditar.getNombre());
        String eleccion = LeerDatosTeclado.leerString("Introduzca un nombre si desea cambiarlo, sino pulse enter: ");
        if (!eleccion.isEmpty()) {
            aEditar.setNombre(eleccion);
        }

        //DNI
        System.out.println("DNI => " + aEditar.getDNI());
        eleccion = LeerDatosTeclado.leerString("Introduzca un DNI si desea cambiarlo, sino, pulse enter");
        while (!eleccion.isEmpty() && !continuar) {
            if (FuncionesValidadoras.validarId(eleccion)) {
                aEditar.setDNI(eleccion);
                continuar = true;
            } else {
                eleccion = LeerDatosTeclado.leerString("Introduzcalo de nuevo o pulse enter");
                if (eleccion.isEmpty()) {
                    continuar = true;
                }
            }
        }

        //Dirección
        System.out.println("Dirección => " + aEditar.getDireccion());
        eleccion = LeerDatosTeclado.leerString("Introduzca una dirección si desea cambiarla, sino, pulse enter");
        if (!eleccion.isEmpty()) {
            aEditar.setDireccion(eleccion);
        }

        //Localidad
        System.out.println("Localidad => " + aEditar.getLocalidad());
        eleccion = LeerDatosTeclado.leerString("Introduzca una localidad si desea cambiarla, sino, pulse enter");
        if (!eleccion.isEmpty()) {
            aEditar.setLocalidad(eleccion);
        }

        //Provincia
        System.out.println("Provincia => " + aEditar.getProvincia());
        eleccion = LeerDatosTeclado.leerString("Introduzca una provincia si desea cambiarla, sino, pulse enter");
        if (!eleccion.isEmpty()) {
            aEditar.setProvincia(eleccion);
        }

        //Codigo Postal
        System.out.println("Código postal => " + aEditar.getCodigoPostal());
        eleccion = LeerDatosTeclado.leerString("Introduzca un código postal si desea cambiarlo, sino, pulse enter");
        continuar = false;
        while (!eleccion.isEmpty() && !continuar) {
            if (eleccion.length() == 5) {
                aEditar.setCodigoPostal(eleccion);
                continuar = true;
            } else {
                eleccion = LeerDatosTeclado.leerString("Introduzcalo de nuevo o pulse enter");
                if (eleccion.isEmpty()) {
                    continuar = true;
                }
            }
        }

        //Teléfono
        System.out.println("Teléfono => " + aEditar.getTelefono());
        eleccion = LeerDatosTeclado.leerString("Introduzca un teléfono si desea cambiarlo, sino, pulse enter");
        continuar = false;
        while (!eleccion.isEmpty() && !continuar) {
            if (eleccion.length() == 9) {
                aEditar.setTelefono(eleccion);
                continuar = true;
            } else {
                eleccion = LeerDatosTeclado.leerString("Introduzcalo de nuevo o pulse enter");
                if (eleccion.isEmpty()) {
                    continuar = true;
                }
            }
        }

        //Fecha Nacimiento
        System.out.println("Fecha nacimiento => " + aEditar.fechaNacToString());
        eleccion = LeerDatosTeclado.leerString("¿Desea cambiar la fecha? Enter para continuar sin cambios");
        if (!eleccion.isEmpty()) {
            int dia = LeerDatosTeclado.leerEntero("Dia de nacimiento", 1, 31);
            int mes = LeerDatosTeclado.leerEntero("Mes de nacimiento", 1, 12);
            int year;
            do {
                year = LeerDatosTeclado.leerEntero("Deme su año de nacimiento");
            } while (Calendar.YEAR - year > 99);
            Calendar fechaNueva = new GregorianCalendar(year, mes, dia);
            aEditar.setFechaNacimiento(fechaNueva);
        }

        //Sexo
        System.out.println("Sexo => " + aEditar.getSexo());
        eleccion = LeerDatosTeclado.leerString("Introduzca H/M si desea cambiarlo, sino, pulse enter");
        if (!eleccion.isEmpty() && eleccion.charAt(0) == 'H') {
            aEditar.setSexo('H');
        } else if (!eleccion.isEmpty() && eleccion.charAt(0) == 'M') {
            aEditar.setSexo('M');
        }

        //Atributos concretos de cada subclase
        if (personas.get(posicion) instanceof Socio) {
            Socio socio = (Socio) personas.get(posicion);
            //Sesiones
            System.out.println("Sesiones =>" + socio.getSesionesSemanales());
            eleccion = LeerDatosTeclado.leerString("Pulse enter para continuar o cualquier caracter para cambiarlas");
            if (!eleccion.isEmpty()) {
                int sesiones = LeerDatosTeclado.leerEntero("Introduzca las sesiones que desea: ", 2, 6);
                socio.setSesionesSemanales(sesiones);
            }
            //Pagado
            if (!socio.isPagado()) {
                eleccion = LeerDatosTeclado.leerString("¿No ha pagado sus cuotas, desea pagarlas? (S/N)");
                if (!eleccion.isEmpty() && eleccion.charAt(0) == 'S') {
                    socio.setPagado(true);
                }
            }
            //Lesiones
            eleccion = LeerDatosTeclado.leerString("Introduzca nuevas lesiones si las tiene, sino, pulse enter");
            if (!eleccion.isEmpty()) {
                socio.setLesiones(eleccion);
            }
        } else if (personas.get(posicion) instanceof Monitor) {
            Monitor monitor = (Monitor) personas.get(posicion);
            //Sueldo
            System.out.println("Sueldo => " + Arrays.toString(monitor.getEspecialidad()));
            eleccion = LeerDatosTeclado.leerString("Desea cambiar el sueldo de " + monitor.getNombre() + "?"
                    + "Pulse enter para continuar sin cambios, cualquier letra para cambiarlo");
            if (!eleccion.isEmpty()) {
                float sueldo = LeerDatosTeclado.leerFloat("Introduzca el sueldo nuevo", 950);
                monitor.setSueldo(sueldo);
            }
            
            //Especialidad
            
            //Activo
            System.out.println("Activo => " + monitor.isActivo());
            eleccion = LeerDatosTeclado.leerString("¿Quiere cambiar su estado actual?"
                    + "Pulse enter para continuar o introduzca cualquier carácter para cambiarlo");
            if (!eleccion.isEmpty()) {
                monitor.setActivo(!monitor.isActivo());
            }
        } else {
            throw new IllegalArgumentException("Class not yet supported");
        }
    }

    /**
     * Función que crea un Calendar según los datos del Usuario
     *
     * @param fechaAlta fecha en la que se dió de alta con la que se validará la
     * edad
     * @return Calendar con la fecha de nacimiento del usuario
     */
    public static Calendar leerFechaNacimiento(Calendar fechaAlta) {
        int dia = LeerDatosTeclado.leerEntero("Dame tu día de nacimiento", 1, 31);
        int mes = (LeerDatosTeclado.leerEntero("Dame tu mes de nacimiento", 1, 12)) - 1;
        int year;
        do {
            year = LeerDatosTeclado.leerEntero("Dame tu año de nacimiento");
        } while (Calendar.YEAR - year > 99);

        return new GregorianCalendar(year, mes, dia);
    }

    /**
     * Función que recibe un String y que busca en un array que le pasamos,
     * devuelve True si el array contiene el String False si el array no lo
     * contiene
     *
     * @param aBuscar String a buscar
     * @param lista ArrayList en el que buscar
     * @return boolean
     */
    public static boolean contains(String aBuscar, ArrayList<String> lista) {
        boolean contiene;
        if (lista.contains(aBuscar)) {
            contiene = true;
        } else {
            contiene = false;
            System.out.println("Ese no está registrado, inténtelo de nuevo");
        }
        return contiene;
    }

    /**
     * Funcion que recibe una string y pregunta al usuario si quiere continuar
     * con los cambios
     *
     * @param contenido contenido a cambiar
     * @param accion 1 => si | 2 => no
     * @return boolean
     */
    public static boolean continuar(String contenido, final int accion) {
        final int ADD = 1;
        final int REMOVE = 0;

        boolean continuar;
        final int SI = 1;

        if (accion == ADD) {
            System.out.println("Vas a añadir => " + contenido + " ¿estas seguro?");
        }

        if (accion == REMOVE) {
            System.out.println("Vas a quitar => " + contenido + " ¿estas seguro?");
        }

        int decision = LeerDatosTeclado.leerEntero("Introduce 1 para si, 0 para no", 0, 1);

        continuar = decision == SI;
        return continuar;
    }

    /**
     * Saca por pantalla una a una las especialidades presentes en el programa
     * En caso de introducir un caracter se borrará en caso contrario no.
     */
    private static void eliminarEspecialidades(ArrayList<String> listaEspecialidades) {
        //Array list en el que guardamos las posiciones de las strings que queremos eliminar
        ArrayList<Integer> aEliminar = new ArrayList<>();

        //Recorremos el array de especialidades una por una, guardando su posicion en cada iteración
        for (String esp : listaEspecialidades) {
            int posicion = listaEspecialidades.indexOf(esp);

            //Se pregunta si se quiere borrar, si se introduce enter pasa al siguiente
            String eleccion = LeerDatosTeclado.leerString("Si quiere eliminar " + esp + " introduzca cualquier carácter");

            /*
            Si se ha introducido algo, se llama a la funcion continuar, que confirma si se desea eleminar, y se añade
            la posición antes guardada al array de posiciones
             */
            if (!eleccion.isEmpty()) {
                if (continuar(esp, 0)) {
                    aEliminar.add(posicion);
                }
            }
        }

        /*
        Contador con el cual sabremos si se ha eliminado previamente otro objeto para que no haya problemas con el
        indice
         */
        int modificador = 0;

        //Se elimina el indice guardado en el array de posiciones
        for (int s : aEliminar) {
            listaEspecialidades.remove(s - modificador);
            modificador++;
        }
    }

    /**
     * Saca por pantalla una a una los trabajos presentes en el programa En caso
     * de introducir un caracter se borrará en caso contrario no.
     */
    public static void eliminarTrabajos(ArrayList<String> listaTrabajos, ArrayList<Persona>personas) {
        //Array list en el que guardamos las posiciones de las strings que queremos eliminar
        //Esto lo hago porque no se pueden eliminar elementos de un array mientras lo recorres
        ArrayList<Integer> posEliminar = new ArrayList<>();

        //Recorremos el array de especialidades una por una, guardando su posicion en cada iteración
        for (String trabajo : listaTrabajos) {
            int posicion = listaTrabajos.indexOf(trabajo);

            //Se pregunta si se quiere borrar, si se introduce enter pasa al siguiente
            String eleccion = LeerDatosTeclado.leerString("Si quiere eliminar " + trabajo + " introduzca cualquier carácter");

            /*
            Si se ha introducido algo, se llama a la funcion continuar, que confirma si se desea eliminar, y se añade
            la posición antes guardada al array de posiciones
             */
            if (!eleccion.isEmpty()) {
                if (continuar(trabajo, 0)) {
                    posEliminar.add(posicion);
                }
            }
        }

        /*
        Contador con el cual sabremos si se ha eliminado previamente otro objeto para que no haya problemas con el
        indice
         */
        int modificador = 0;

        //Se elimina el indice guardado en el array de posiciones
        for (int s : posEliminar) {
            listaTrabajos.remove(s - modificador);
            modificador++;
        }
    }
}
