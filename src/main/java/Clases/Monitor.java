package Clases;

import java.util.Arrays;
import java.util.Calendar;

public class Monitor extends Persona {

    private String[] especialidades;
    private float sueldo;
    private boolean activo;

    public Monitor(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo, String[] especialidades, float sueldo, boolean activo) {
        super(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);

        if (sueldo < 950) {
            throw new IllegalArgumentException("El sueldo es inválido");
        }
        
        if (especialidades.length > 3) {
            throw new IllegalArgumentException("No puede tener más de 3 especialidades");
        }

        this.sueldo = sueldo;
        this.especialidades = Arrays.copyOf(especialidades, especialidades.length);
        this.activo = activo;
    }

    @Override
    public String toString() {
        return super.toString() + "Especialidad = " + Arrays.toString(this.especialidades) + "Sueldo = " + sueldo
                + "Activo = " + activo;
    }

    public String[] getEspecialidad() {
        return especialidades;
    }

    public boolean setEspecialidad(String especialidad) {
        boolean continuar = true;
        boolean added = false;

        for (int i = 0; i < this.especialidades.length && continuar; i++) {
            if (this.especialidades[i] == null) {
                this.especialidades[i] = especialidad;
                added = true;
                continuar = false;
            }
        }
        return added;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        if (sueldo < 950) {
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
