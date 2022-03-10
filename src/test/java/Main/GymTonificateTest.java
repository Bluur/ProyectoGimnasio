
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
     * Test of main method, of class GymTonificate.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        GymTonificate.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of altaPersona method, of class GymTonificate.
     */
    @Test
    public void testAltaPersona() {
        System.out.println("altaPersona");
        int tipo = 0;
        Persona expResult = null;
        Persona result = GymTonificate.altaPersona(tipo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarPersona method, of class GymTonificate.
     */
    @Test
    public void testModificarPersona() {
        System.out.println("modificarPersona");
        int posicion = 0;
        GymTonificate.modificarPersona(posicion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

    /**
     * Test of contains method, of class GymTonificate.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String aBuscar = "";
        ArrayList<String> lista = null;
        boolean expResult = false;
        boolean result = GymTonificate.contains(aBuscar, lista);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of continuar method, of class GymTonificate.
     */
    @Test
    public void testContinuar() {

    }

    /**
     * Test of eliminarEspecialidades method, of class GymTonificate.
     */
    @Test
    public void testEliminarEspecialidades() {
        System.out.println("eliminarEspecialidades");
        GymTonificate.eliminarEspecialidades();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarTrabajos method, of class GymTonificate.
     */
    @Test
    public void testEliminarTrabajos() {
        System.out.println("eliminarTrabajos");
        GymTonificate.eliminarTrabajos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
