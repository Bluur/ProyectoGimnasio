
package Clases;


import java.util.Calendar;

public class Socio extends Persona {
    private double cuota;
    private int sesionesSemanales;
    private boolean pagado;
    private String lesiones;

    public Socio(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, Calendar fechaAlta, Calendar fechaNacimiento, char sexo, int sesionesSemanales, boolean pagado, String lesiones){
        super(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);

        if(sesionesSemanales < 2 || sesionesSemanales > 6){
            throw new IllegalArgumentException("El número de sesiones es inválido");
        }

        this.sesionesSemanales = sesionesSemanales;
        this.cuota = sesionesSemanales * 8;
        this.pagado = pagado;
        this.lesiones = lesiones;
    }

    public String toString(){
        return super.toString() + "Cuota semanal = " + cuota + "Sesiones semanales =" + sesionesSemanales + "Pagado = "
                + pagado + "Lesiones conocida = " + lesiones;
    }

    public double getCuota() {
        return cuota;
    }

    public int getSesionesSemanales() {
        return sesionesSemanales;
    }

    public void setSesionesSemanales(int sesionesSemanales) {
        if(sesionesSemanales < 2 || sesionesSemanales > 6){
            throw new IllegalArgumentException("El número de sesiones no es válido");
        }

        this.sesionesSemanales = sesionesSemanales;
        this.cuota = sesionesSemanales * 8;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public String getLesiones() {
        return lesiones;
    }

    public void setLesiones(String lesiones) {
        this.lesiones += lesiones;
    }

}
