
package Main;

import Clases.Persona;
import java.util.ArrayList;
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

/**
 *
 * @author Migue
 */
public class GymTonificateTest {
    
    public GymTonificateTest() {
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


    /**
     * Test of leerFechaNacimiento method, of class GymTonificate.
     * @param dia
     * @param mes
     * @param year
     */
    @ParameterizedTest
    @CsvSource({
        "17, 8, 1999"
    })
    public void testLeerFechaNacimiento(int dia, int mes, int year) {
        Calendar fechaAlta = new GregorianCalendar(year, mes, dia);
        Calendar prueba = GymTonificate.leerFechaNacimiento(fechaAlta);
        assertTrue(prueba.get(Calendar.YEAR) == 1999);
    }

}
