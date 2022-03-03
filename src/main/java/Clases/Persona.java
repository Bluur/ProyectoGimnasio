
package Clases;

import Funciones.funcionesValidadoras;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public abstract class Persona implements Comparable<Persona> {
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
        
        if(!funcionesValidadoras.validarId(DNI)){
            throw new IllegalArgumentException("El DNI no es válido");
        }

        if(codigoPostal.length() != 5){
            throw new IllegalArgumentException("El código postal español debe constar de 5 dígitos");
        }
        
        if(telefono.length() != 9){
            throw new IllegalArgumentException("El teléfono debe de tener de 9 dígitos");
        }
                
        if(fechaAlta.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR) > 99){
            throw new IllegalArgumentException("La edad no puede ser mayor que 99");
        }

        if(sexo != 'H' && sexo != 'M'){
            throw new IllegalArgumentException("El sexo debe ser H o M");
        }

        this.nombre = nombre;
        this.DNI = DNI;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.fechaAlta = new GregorianCalendar(fechaAlta.get(Calendar.YEAR), fechaAlta.get(Calendar.MONTH), fechaAlta.get(Calendar.DAY_OF_WEEK));
        this.fechaNacimiento = new GregorianCalendar(fechaNacimiento.get(Calendar.YEAR), fechaNacimiento.get(Calendar.MONTH), fechaNacimiento .get(Calendar.DAY_OF_WEEK));
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        if(!funcionesValidadoras.validarId(DNI)){
            throw new IllegalArgumentException("El DNI no es válido");
        }
        this.DNI = DNI;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        if(codigoPostal.length() != 5){
            throw new IllegalArgumentException("El código postal español debe constar de 5 dígitos");
        }
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(telefono.length() != 9){
            throw new IllegalArgumentException("El teléfono debe tener 5 dígitos");
        }
        this.telefono = telefono;
    }

    public Calendar getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaEntrada) {
        if(Calendar.YEAR - getEdad() > 99){
            throw new IllegalArgumentException("La edad no puede ser mayor que 99");
        }
        this.fechaNacimiento = fechaEntrada;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        if (sexo != 'H' && sexo != 'M') {
            throw new IllegalArgumentException("El sexo debe ser H o M");
        }
        this.sexo = sexo;
    }

    public int getEdad(){
        return fechaAlta.get(Calendar.YEAR) - this.fechaNacimiento.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", DNI='" + DNI + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo=" + sexo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(DNI, persona.DNI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaAlta, fechaNacimiento, sexo);
    }

    public int compareTo(Persona o){
        final int edad;
        if(getEdad() == o.getEdad()){
            edad = 0;
        }else if(getEdad() < o.getEdad()){
            edad = -1;
        }else {
            edad = 1;
        }
        return edad;
    }
}
