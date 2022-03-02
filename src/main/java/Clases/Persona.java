
package Clases;

import Funciones.funcionesValidadoras;
import java.util.Calendar;

public abstract class Persona {
    private String nombre;
    private String DNI;
    private String direccion;
    private String localidad;
    private String provincia;
    private String codigoPostal;
    private String telefono;
    private Calendar fechaAlta;
    private Calendar fechaNacimiento;
    private char sexo;
    
    public Persona(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo){
        if(!nombre.isEmpty() || !nombre.isBlank()){
            throw new IllegalArgumentException("El nombre está vacío y no es válido");
        }
        
        if(!funcionesValidadoras.validarId(DNI)){
            throw new IllegalArgumentException("Su DNI no es válido");
        }
        
        if(!direccion.isEmpty() && !direccion.isBlank()){
            throw new IllegalArgumentException("La dirección está vacía y no es válida");
        }
        
        if(!localidad.isEmpty() && !localidad.isBlank()){
            throw new IllegalArgumentException("La localidad está vacía y no es válida");
        }
        
        if(!provincia.isEmpty() && !provincia.isBlank()){
            throw new IllegalArgumentException("La provincia está vacía y no es válida");
        }
        
        if(codigoPostal.length() != 5){
            throw new IllegalArgumentException("El código postal español debe constar de 5 dígitos");
        }
        
        if(telefono.length() != 9){
            throw new IllegalArgumentException("El teléfono debe de ser de 9 dígitos");
        }

        
        this.nombre = nombre;
        this.DNI = DNI;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }
}
