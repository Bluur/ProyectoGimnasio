
package Clases;

import java.util.Calendar;

public class Empleado extends Persona{

    private String tipoTrabajo;
    private float sueldo;
    private String extension;

    public Empleado(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo, String tipoTrabajo, float sueldo, String extension){
        super(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);

        if(sueldo < 950){
            throw new IllegalArgumentException("El sueldo es inválido");
        }

        this.tipoTrabajo = tipoTrabajo;
        this.sueldo = sueldo;
        this.extension = extension;
    }

    public String toString(){
         return super.toString() + ", Tipo de Trabajo = " + this.tipoTrabajo + ", Sueldo = " + this.sueldo
                + ", Extensión telefónica = " + this.extension;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        if(sueldo < 950){
            throw new IllegalArgumentException("El sueldo es inválido");
        }
        this.sueldo = sueldo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
