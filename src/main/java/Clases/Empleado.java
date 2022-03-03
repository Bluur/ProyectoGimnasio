
package Clases;

import java.util.Calendar;

public class Empleado extends Persona{
    
    public Empleado(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo){
        super(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);
    }

}
