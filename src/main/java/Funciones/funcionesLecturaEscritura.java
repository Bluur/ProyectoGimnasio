
package Funciones;

import Clases.Persona;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class funcionesLecturaEscritura {

    /**
     * Lee de un archivo binario un objeto de tipo ArrayList de la clase 
     * persona
     * @param archivo String con el nombre del archivo a editar
     * @return ArrayList con los datos obtenidos
     */
    public static ArrayList<Persona> leerDatosArrayList(String archivo){
        ObjectInputStream flujoEntrada = null;
        ArrayList<Persona> personas = null;
        try{
            flujoEntrada = new ObjectInputStream(new FileInputStream(archivo));
            personas = (ArrayList<Persona>) flujoEntrada.readObject();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException e2){
            System.out.println(e2.getMessage());
        }finally{
            if(flujoEntrada != null){
                try{
                    flujoEntrada.close();
                }catch(IOException e3){
                    System.out.println(e3.getMessage());
                }
            }
        }
        return personas;
    }
    
    /**
     * Guarda en un archivo binario un objeto de tipo ArrayList de la clase
     * Persona
     * @param personas ArrayList a Guardar
     * @param archivo String con el nombre del archivo
     */
    public static void guardarDatosArrayList(ArrayList<Persona> personas, String archivo){
        ObjectOutputStream flujoSalida = null;
        try{
            flujoSalida = new ObjectOutputStream(new FileOutputStream(archivo));
            flujoSalida.writeObject(personas);
        }catch(IOException e2){
            System.out.println(e2.getMessage());
        }finally{
            if(flujoSalida != null){
                try{
                    flujoSalida.close();
                }catch(IOException e3){
                    System.out.println(e3.getMessage());
                }
            }
        }
    }
    
    /**
     * Lee de un archivo binario un objeto ArrayList de la clase String
     * @param archivo String ocn el nombre del archivo
     * @return Objeto ArrayList
     */
    public static ArrayList<String> leerDatosArrayString(String archivo){
        ObjectInputStream flujoEntrada = null;
        ArrayList<String> lista = null;
        try{
            flujoEntrada = new ObjectInputStream(new FileInputStream(archivo));
            lista = (ArrayList<String>)flujoEntrada.readObject();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException e2){
            System.out.println(e2.getMessage());
        }finally{
            if(flujoEntrada != null){
                try{
                    flujoEntrada.close();
                }catch(IOException e3){
                    System.out.println(e3.getMessage());
                }
            }
        }
        return lista;
    }
    
    /**
     * Guarda un objeto ArrayList de la clase String
     * @param lista ArrayList con las Strings a guardar
     * @param archivo Nombre del archivo en el que guardar
     */
    public static void guardarDatosArrayString(ArrayList<String> lista, String archivo){
        ObjectOutputStream flujoSalida = null;
        try{
            flujoSalida = new ObjectOutputStream(new FileOutputStream(archivo));
            flujoSalida.writeObject(lista);
        }catch(IOException e2){
            System.out.println(e2.getMessage());
        }finally{
            if(flujoSalida != null){
                try{
                    flujoSalida.close();
                }catch(IOException e3){
                    System.out.println(e3.getMessage());
                }
            }
        }
    }
}
