
package Clases;

import java.util.Calendar;

public class Monitor extends Persona {

    private String especialidad;
    private float sueldo;
    private boolean activo;

    public Monitor(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo, String especialidad, float sueldo, boolean activo){
        super(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);

        if(sueldo < 950){
            throw new IllegalArgumentException("El sueldo es inválido");
        }

        this.sueldo = sueldo;
        this.especialidad = especialidad;
        this.activo = activo;
    }

    public String toString(){
        return super.toString() + ", Especialidad = " + this.especialidad + ", Sueldo = "+ sueldo
                + ", Activo = " + activo + '}';
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        if(sueldo < 950){
            throw new IllegalArgumentException("El sueldo no es válido");
        }
        this.sueldo = sueldo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
