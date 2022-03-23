
package Funciones;

import Clases.Persona;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class funcionesLecturaEscritura {

    public static ArrayList<Persona> leerDatos(String archivo){
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
    
    public static void guardarDatos(ArrayList<Persona> personas, String archivo){
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
}
