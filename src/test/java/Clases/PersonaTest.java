
package Clases;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class PersonaTest {

    
    public PersonaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {

    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        
    }
    
    @ParameterizedTest
    @CsvSource({
        "Miguel, 77446461, Calle almuñecar, Granada, Granada, 18006, 958945139, H, El DNI no es válido",
        "Miguel, 77446461X, Calle almuñecar, Granada, Granada, 0099, 958945139, H, El código postal español debe constar de 5 dígitos",
        "Miguel, 77446461X, Calle almuñecar, Granada, Granada, 18006, 99878388, H, El teléfono debe de tener de 9 dígitos"
    })
    public void testConstructor(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, char sexo, String expRes){
        Exception excepcion = assertThrows(IllegalArgumentException.class, () ->{
        });
        
        assertEquals(expRes, excepcion.getMessage());
        
    }
    
    @ParameterizedTest
    @CsvSource({
        "Miguel, 77446461X, Calle almuñecar, Granada, Granada, 18006, 958945139, H, La edad no puede ser mayor que 99"
    })
    public void testConstructorFecha(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, char sexo, String expRes){
        Exception excepcion = assertThrows(IllegalArgumentException.class, () ->{
        });
        
        assertEquals(expRes, excepcion.getMessage());
    }

    @Test
    public void testGetEdad(){

    }
    
}
